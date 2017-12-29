package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Campground;

class CampgroundIdDeserializer extends IdDeserializer<Campground> {
    public CampgroundIdDeserializer() {
        super(Campground::setId, Campground.class);
    }
}
