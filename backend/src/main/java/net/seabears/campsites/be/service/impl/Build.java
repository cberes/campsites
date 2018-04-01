package net.seabears.campsites.be.service.impl;

import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.CampsiteAvailability;
import net.seabears.campsites.be.domain.DateAvailability;
import net.seabears.campsites.enums.Availability;

import java.time.LocalDate;
import java.util.List;

final class Build {
    private Build() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    static CampgroundAvailability campground(final long campgroundId, final List<CampsiteAvailability> campsites) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(campgroundId);
        availability.setCampsites(campsites);
        return availability;
    }

    static CampsiteAvailability campsite(final long campsiteId, final List<DateAvailability> dates) {
        final CampsiteAvailability availability = new CampsiteAvailability();
        availability.setId(campsiteId);
        availability.setAvailability(dates);
        return availability;
    }

    static DateAvailability date(final LocalDate date, final Availability status) {
        final DateAvailability availability = new DateAvailability();
        availability.setDate(date);
        availability.setStatus(status);
        return availability;
    }
}
