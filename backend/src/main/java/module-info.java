module net.seabears.campsites.services {
    exports net.seabears.campsites.be;
    exports net.seabears.campsites.be.dao;
    exports net.seabears.campsites.be.domain;
    exports net.seabears.campsites.be.service;

    requires spring.core;
    requires spring.context;
    requires spring.data.commons;
    requires net.seabears.campsites.core;
    requires net.seabears.campsites.database;
}