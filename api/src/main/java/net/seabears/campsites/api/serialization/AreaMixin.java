package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;

import java.util.Set;

abstract class AreaMixin {
    @JsonProperty("campgroundId")
    @JsonDeserialize(using = CampgroundIdDeserializer.class)
    @JsonSerialize(using = CampgroundIdSerializer.class)
    abstract Campground getCampground();

    @JsonIgnore
    abstract Set<Campsite> getCampsites();
}
