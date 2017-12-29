module net.seabears.campsites.database {
    exports net.seabears.campsites.db;
    exports net.seabears.campsites.db.domain;

    requires hibernate.jpa;
    requires net.seabears.campsites.core;
}