module net.seabears.campsites.api {
    exports net.seabears.campsites.api;
    exports net.seabears.campsites.api.config;
    exports net.seabears.campsites.api.controllers;
    exports net.seabears.campsites.api.controllers.advice;
    exports net.seabears.campsites.api.filters;
    exports net.seabears.campsites.api.serialization;
    opens net.seabears.campsites.api to spring.core;
    opens net.seabears.campsites.api.config to spring.core;
    opens net.seabears.campsites.api.controllers to spring.beans;
    opens net.seabears.campsites.api.controllers.advice to spring.beans;
    opens net.seabears.campsites.api.filters to spring.core;
    opens net.seabears.campsites.api.serialization to com.fasterxml.jackson.databind;

    requires javax.transaction.api;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires hibernate.core;
    requires jackson.annotations;
    requires spring.beans;
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