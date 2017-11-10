package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;

import java.util.Set;

abstract class CampgroundMixin {
    @JsonIgnore
    abstract Set<Area> getAreas();

    @JsonIgnore
    abstract Set<Campsite> getCampsites();
}
