package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Reservation;

import java.util.Set;

abstract class CampsiteMixin {
    @JsonProperty("areaId")
    @JsonSerialize(using = AreaIdSerializer.class)
    // TODO deserializer
    abstract Area getArea();

    @JsonProperty("campgroundId")
    @JsonSerialize(using = CampgroundIdSerializer.class)
    // TODO deserializer
    abstract Campground getCampground();

    @JsonIgnore
    abstract Set<Reservation> getReservations();
}
