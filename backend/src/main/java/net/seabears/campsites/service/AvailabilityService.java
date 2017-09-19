package net.seabears.campsites.service;

import net.seabears.campsites.domain.CampgroundAvailability;

import java.util.Date;

public interface AvailabilityService {
    CampgroundAvailability findAvailabilityForCampground(String id, Date start, Date end);

    CampgroundAvailability findAvailabilityForArea(String id, Date start, Date end);

    CampgroundAvailability findAvailabilityForCampsite(String id, Date start, Date end);
}
