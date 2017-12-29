package net.seabears.campsites.be.service;

import net.seabears.campsites.be.domain.CampgroundAvailability;

import java.time.LocalDate;

public interface AvailabilityService {
    CampgroundAvailability findByCampgroundId(long id, LocalDate start, LocalDate end);

    CampgroundAvailability findByAreaId(long id, LocalDate start, LocalDate end);

    CampgroundAvailability findByCampsiteId(long id, LocalDate start, LocalDate end);
}
