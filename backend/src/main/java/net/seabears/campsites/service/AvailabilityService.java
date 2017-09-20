package net.seabears.campsites.service;

import net.seabears.campsites.domain.CampgroundAvailability;

import java.time.LocalDate;

public interface AvailabilityService {
    CampgroundAvailability findAvailabilityForCampground(String id, LocalDate start, LocalDate end);

    CampgroundAvailability findAvailabilityForArea(String id, LocalDate start, LocalDate end);

    CampgroundAvailability findAvailabilityForCampsite(String id, LocalDate start, LocalDate end);
}
