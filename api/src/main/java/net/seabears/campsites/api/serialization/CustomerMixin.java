package net.seabears.campsites.api.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.seabears.campsites.db.domain.Reservation;

import java.util.Set;

abstract class CustomerMixin {
    @JsonIgnore
    abstract Set<Reservation> getReservations();
}
