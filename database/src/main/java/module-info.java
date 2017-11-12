module net.seabears.campsites.database {
    exports net.seabears.campsites.db;
    exports net.seabears.campsites.db.domain;
    exports net.seabears.campsites.db.converters;

    requires hibernate.jpa;
    requires net.seabears.campsites.core;
}