---
title: "Java 10"
date: 2018-04-01T23:20:31-04:00
---

I was happy to get my hands on a copy of the third edition of "Effective Java" by [Joshua Bloch](https://twitter.com/joshbloch). Near the end of chapter 4, he concludes his thoughts on modules as follows

> It is too early to say whether modules will achieve widespread use outside of the JDK itself. In the meantime, it seems best to avoid them unless you have a compelling need.

I decided I have no compelling need to use Java's module system, so I have removed the module declarations from my project. It was an easy change to complete--I believe it took less than an hour. Now I can run tests via Gradle and IntelliJ. No hoops to jump through.

I updated my project to build with Java 10. I was surprised to see that it was released so soon after Java 9. I learned that OpenJDK has adopted a more regular release cycle. I have yet to leverage the new `var` keyword, but I can imagine some uses for it (long field declarations with nested generics, to name one).

Also, I have implemented a service to determine when campsites are available based on reservations persisted to a database. Previously, the project used only mock availability data. I had initially worked on this a few months ago before I took a long break from this project. I revised the code, cleaned it up, and tested it.

There are still a few changes needed for the availability service.

- The system queries for every single reservation record. This is unacceptable, but I need to learn more about Spring's JPA repositories so that I can write the proper query.
- The system will prohibit reserving a campsite less than 2 days in advance. I want this number of days to be a configurable setting.
- I want to allow campsites to be listed as "first-come, first-served" always.

Next I want to focus on the frontend. I have said it before: I need to clean up the frontend because it has zero frills. Through work I learned about [Element](http://element.eleme.io). I would like to incorporate this library into the frontend. This will allow me to learn more about the library.

I would like to add the UI components that will allow a reservation to be created. I think this is the next logical step in make the campsites project into a usable product. Also, it will let me leverage the database availability services that I just completed.

Finally, I would like to host a version of the frontend at [awayfromho.me](http://awayfromho.me). This version should use mock services to persist data in the browser. Thus I will not need to pay for a server to run the Java/Spring backend.

I hope that working on these frontend tasks will let me build some momentum. I've been very slow to progress with this project. Work on the backend is very similar to the work I do everyday for a living, so it has been tough to motivate myself to do more of that work for free.
