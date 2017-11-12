module net.seabears.campsites.api {
    requires javax.transaction.api;
    requires hibernate.core;
    requires postgresql;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.jdbc;
    requires spring.orm;
    requires spring.web;
    requires spring.webmvc;
    requires tomcat.embed.core;
    requires net.seabears.campsites.core;
    requires net.seabears.campsites.database;
    requires net.seabears.campsites.services;
}