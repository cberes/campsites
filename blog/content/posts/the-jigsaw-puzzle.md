---
title: "The Jigsaw Puzzle"
date: 2017-11-12T17:46:29-05:00
---

Since my last update at the beginning of October, I updated the REST controllers to read data from a database (PostgreSQL in this case). Previously they read from data stored in a Java array.

## Querying the database

Spring makes it [very easy](https://spring.io/guides/gs/accessing-data-jpa/) to read from a database using JPA. I chose to use this approach. Spring handles all of the implementation. It does the basics (create, read, update, delete), and it can generate more complex queries based on functions' names. I'm not sure if I can write queries manually using this approach, but at this point, I don't know what queries I will need.

Also, I decided to use Hibernate to create and update the database automatically. This will be really useful if I ever need to update the database in between versions of the project. Originally I planned to use [Apache's DDL Utils](https://db.apache.org/ddlutils/). It's used at my current job, and it makes database updates much easier than the manual system of SQL scripts used by my previous employer. But I had trouble telling DDL Utils to use a UUID type for primary keys. On the other hand, Hibernate makes it easy to use UUID primary keys. Except for the UUID type, DDL Utils seems to be more flexible than Hibernate. But I think the benefits of UUID primary keys outweigh Hibernate's disadvantages.

## REST controllers

Spring can also [generate REST controllers automatically](https://spring.io/guides/gs/accessing-data-rest/). I tried this approach, but I will not use it. It uses the HAL format for responses. I don't understand the HAL format's advantages. It also feels very inflexible. I was able to configure Spring to include foreign keys in the REST responses, but I still wasn't happy with the outcome.

Instead I decided to write the REST controllers manually. The methods are still very small. However, it added the challenge of converting the database entities to the POJOs that represented the REST response formats I desired. I planned on manually writing adapters for each type. That solution turned out to be very verbose, and it would have required updates whenever the entities changed. I preferred a more elegant solution.

I researched custom serializers in Jackson. Again, that solution was too manual. I considered using [Gson](https://github.com/google/gson/blob/master/UserGuide.md#user-defined-exclusion-strategies) to customize the REST responses, but then I learned about Jackson's [mixin annotations](https://github.com/FasterXML/jackson-docs/wiki/JacksonMixInAnnotations). This feature allowed me to keep the JSON serialization separate from the JPA annotations. It let me write one serializer and apply to the few fields where it was required, e.g. foreign keys. Just as easily I could tell Jackson to ignore the fields that should be ignored, e.g. fields annotated with `@ManyToOne`.

## Jigsaw

I separated the "backend" module into a new "backend" module, which contains only the DAOs and service, and an "api" module, which contains REST controllers. I believe this will be beneficial if I decide to make the data accessible by other means, such as a command-line program or a GUI. And I figure it will be easier to merge separate projects than it will be to separate them later on.

Next, I created Jigsaw modules for each of my Gradle modules. At first it was confusing, but then I learned that Intellij tells you which modules are missing. I went though the arduous process of excluding modules that split packages. Even with my very small project, this took an entire day. Although it might have been quicker if I had prior experience with this.

I followed Gradle's guide on [Java 9 modules](https://guides.gradle.org/building-java-9-modules/). At the end, they introduced a plugin that handles the modules automatically. However, the plugin assumes you will use Junit 4. So that was out of the question. It also assumes you have no modules to patch.

I was excited to learn why I was seeing `NoClassDefFoundError`s about `JAXBException`. The `java.xml.bind` module is not included by default. That turned out to be one of the easier problems to solve. There were many other complications.

- Javax.transaction-api 1.2 contains a split package. I assume there is a way to fix this using the `--patch-module` option, but I could not figure it out. The best solution seems to be to wait for [version 1.3](https://javaee.groups.io/g/jta-spec/topic/welcome_and_changes_to_jta_xa/6336504) if you can't exlude the dependency from your project.
- Part of Tomcat conflicts with Hibernate. Luckily the Tomcat module could be excluded without removing all of Tomcat.
- Hamcrest-library splits a package with Hamcrest-core. I excluded Hamcrest-library. Now I'm exclusively using matches from `CoreMatchers`.
- Similarly, jsonassert splits a package with android-json. I'm not using either of these directly, so I excluded android-json, which seems to work.
- Javassist versions before 3.22.0-GA are incompatible with module-info.java files.
- For that matter, the version of Checkstyle I'm using is incompatible with module-info.java files. It was easy to tell Checkstyle to ignore them.

## Junit 5

The final complication was Junit 5. As soon I created modules, I could no longer run tests with Intellij or Gradle. In Intellij, I still get the error that JAXBException is missing. Tests run by Gradle were in the same boat until I realized that my module configuration for the standard test task was not applied to the Junit Platform Gradle plugin. I had to switch to the latest snapshot version of the Gradle plugin, set the `enableModulePath` property, and update to the latest snapshot version of Junit 5.

Once I resolved that error, I encountered the javax.transaction-api error. I fiddled around with the `--patch-module` argument for awhile without success. Finally, I learned more about the issue: the `javax.transaction.xa` package was included with the `java.sql` module in Java 9. I learned that the JTA folks are planning a new version without that package. They announced these plans 3 days ago. I can't wait, so I built my own copy of this project with the offending package removed. Hopefully that doesn't violate any license.

Those changes brought me to my current predicament: trying to decipher this error from Junit:

```
Error: Could not find or load main class 
Caused by: java.lang.ClassNotFoundException: 
```

That text is printed to `System.err` with no stack trace or additional output. It happens when I use the Junit Platform Gradle plugin. I tried without success to find more information. I adapted [Junit's own sample](https://github.com/junit-team/junit5-samples/tree/master/junit5-java9-engine) into my project. I thought I had solved my previous error when I found that Spring is trying to load a class from TestNG! That's probably a clue to underlying problem. Maybe Spring is not ready for Junit 5 (it is according to their [JIRA](https://jira.spring.io/browse/SPR-13575))? At first I had excluded Junit 4, then I tried to include Junit 4 when I found the TestNG error to no avail. So I included TestNG and I was able to run the Junit 5 console launcher from Gradle. However, none of my tests run.

So at the moment, I cannot run any tests. I've decided that I'll focus on the frontend while things are ironed out with Java 9, Spring Boot 2, and Junit 5.

## Reflections

Some of the decisions I made have made much more work for myself: UUID primary keys, separating the project into separate modules, Spring Boot 2, Junit 5, and Java 9. If I used one large module with integer keys with Spring Boot 1.5, Junit 4, and Java 8, then I would have had a project that reads from the database with working unit tests in a fraction of the time. But this is a personal project, and my goals are to learn new things and to have fun.
