package net.seabears.campsites.be.service;

import net.seabears.campsites.be.domain.CampgroundAvailability;

import java.time.LocalDate;

public interface AvailabilityService {
    CampgroundAvailability findByCampgroundId(String id, LocalDate start, LocalDate end);

    CampgroundAvailability findByAreaId(String id, LocalDate start, LocalDate end);

    CampgroundAvailability findByCampsiteId(String id, LocalDate start, LocalDate end);
}
