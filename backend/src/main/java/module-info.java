module net.seabears.campsites.services {
    exports net.seabears.campsites.be;
    exports net.seabears.campsites.be.dao;
    exports net.seabears.campsites.be.domain;
    exports net.seabears.campsites.be.service;
    exports net.seabears.campsites.be.service.impl;
    opens net.seabears.campsites.be.service.impl to spring.core;

    requires hibernate.jpa;
    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.data.commons;
    requires net.seabears.campsites.core;
    requires net.seabears.campsites.database;
}