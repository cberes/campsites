---
title: "Piecing Together the Puzzle"
date: 2018-01-02T21:23:49-05:00
---

I have fixed every test in the campsites project, and I even added new tests. The tests were broken by a combination of things: Java 9's module system, switching from mocked data to a real database, and some confusion with Junit 5. Since my last post, I fixed those problems.

## Junit 5

The biggest blocker that I faced was my failure to get my tests running. I decided to use Junit 5. My lack of experience with Junit 5 _and_ Java 9's module system made it a challenge to get my unit tests passing.

I think some time away from the problem cleared my mind. I realized that Junit's new Gradle plugin uses the classpath (as opposed to the module system) when running tests. I thought Junit was ignoring my modules, because the class `javax.xml.bind.JAXBException` was not found. But it turns out I merely needed to add a new dependency, `javax.xml.bind:jaxb-api`.

I'm not sure if the Gradle plugin is ready for the module system. The plugin can run tests with javac's `--scan-modules` argument, but I'm not familiar with that argument. Also, I abandoned my plan to execute tests with Junit's console runner, but I tried that method only because I failed to run tests using Junit's new plugin.

Futhermore, I addressed some dependency problems I caused while trying to fix package splits. I solved this by adding artifacts such as `com.vaadin.external.google:android-json` as runtime test dependencies. I think a better solution would require using javac's `--patch-module` option, but I have yet to get that to work.

## Serving up new test slices

I learned of some of [Spring's improvements to testing](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4), and I applied those improvements to my project.

First I converted my REST controller tests such that they test only the MVC slice by using the `@WebMvcTest` annotation. I learned a few things about this annotation's use.

- I needed to add a class annotated with `@SpringBootApplication` in the same package as the controllers I was testing. I'm not exactly sure why. Without this class, Spring tried to instantiate beans in the controllers that were not under test. I encountered the same problem when I tried moving the tests to a different package.
- I had to remove some assertions that depended on custom JSON serialization using Jackson. That's because only the MVC slice is addressed in these tests. That's fine, because I can test the JSON serialization in other tests.
- The endpoints cannot be accessed with the prefix specified by the `server.servlet.context-path` property. Again, that's fine, because I can test the path in other tests, e.g. integration tests.
- DAOs should be mocked with Mockito.

Next, I created tests for the project's JPA repositories using the `@DataJpaTest` annotation. I learned that this annotation should be used only for testing JPA\-\-Spring will not setup other components. Use `@AutoConfigureTestDatabase` to tell Spring to setup an embedded database for general usage within tests.

Finally, I tested my custom JSON serialization using the `@JsonTest` annotation. I had created a Jackson `SimpleModule` for serialization, which I injected with a `WebMvcConfigurer` instance. To get these tests working, I needed to convert the module to a Spring bean. Spring knew what to do with the bean once I added the `@Component` annotation to the module.

## Mocked data

I ended up with tests inside two modules (backend and api) that created mock data. I could have put all the data mocking code inside backend's test module, created a test JAR for this module, and added the test JAR as a dependency for the api test module. Instead, I created new module, which I named "test-utils." Each of backend and api's test modules depend on this module. I like this solution, because Gradle does not make test JARs as easy as Maven does.

## Fixed integration tests

I fixed the campsites project's integration tests, which have not worked since I updated the project to read campsites from a database. The integration tests are a useful opportunity to test several components in concert\-\-controllers, DAOs, serialization, and Spring properties.

Rather than requiring a Postgres database to be running, I used the `@AutoConfigureTestDatabase` to tell Spring to create an embedded database for the tests. The advantage is that tests can be run without external dependencies. The obvious disadvantage is that interaction with Postgres is untested. However, because Gradle does not differentiate between unit tests, integration tests, and other kinds of tests unless you tell it to, I could easily create another set of integration tests that leverage a real Postgres database.

I did notice some kinks with use of the `@AutoConfigureTestDatabase` annotation. Spring should have created the database by default, because I had specified that an embedded database (H2) should be used. However, I had to use `@TestPropertySource` to set the property `spring.jpa.hibernate.ddl-auto` to `create-drop`.

## Bigint primary keys over UUID

I decided to use BIGINT primary keys instead of UUID primary keys. I had choden UUID keys because it would allow users to identity campgrounds and such with text keys instead of serial, numeric keys. I had a change of heart, and I decided that this advantage was not worth the potential disadvantages\-\-slower database accesses, potential problems using a different database, etc. As I previously noted, I'm using H2 for integration tests and Postgres for production, so this is a realistic concern.

If I decide that I need to refer to campgrounds and campsites by text keys that are not serial, I can find another way to do so while retaining numeric keys. Besides, UUIDs are not very elegant aesthetically.

## Checkstyle

I encountered build errors caused by Checkstyle. The `METHOD_REF` constant was not found, which indicated that I was not using a recent enough version of Checkstyle. However, I had specified version 8.2 of the tool, which should be fine. However, the error seems to be gone now.

I was unsure if Checkstyle was even running, because I saw no warnings in the build output.  Fortunately, Checkstyle was active, but there were no warings. I looked over its configuration, and I added more rules. There probably are more rules that I should add.

## Remaining problems

To run my project, I need to specify the `spring.config.location` command-line argument. I do not know why this is, and I don't know where to start looking. I may be able to debug the problem using IntelliJ. Spring should use the `application.properties` file in the api module's resources. I thought the file might not be visible because of Java's module system, but according to the Java API, it should not be encapsulated, because the file is at the root level.

## What's next

Now that I have data read from a database and my original tests running, I would like to add new components on the Java side:

- get availability from the database
- controller for reservations

This would let me add a _very simple_ shopping cart to the frontend. Then campsites can be reserved and availability changes can be observed. Furthermore, the frontend desperately needs some styling. It's hideous to look at. I've been putting this off, because I rarely design websites nowadays.

Once I address those problems, I'd like to build a version of the frontend with mock data (i.e. no backend application) so that I can host it on github-pages at [awayfromho.me](http://awayfromho.me). I would love to have a version of the project available online, but I don't want to pay a monthly fee for this.

