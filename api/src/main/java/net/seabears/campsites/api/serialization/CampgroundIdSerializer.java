package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Campground;

class CampgroundIdSerializer extends IdSerializer<Campground> {
    public CampgroundIdSerializer() {
        super(Campground::getId);
    }
}
