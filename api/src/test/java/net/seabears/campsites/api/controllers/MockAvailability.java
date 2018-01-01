package net.seabears.campsites.api.controllers;

import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.CampsiteAvailability;
import net.seabears.campsites.be.domain.DateAvailability;
import net.seabears.campsites.enums.Availability;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

final class MockAvailability {
    private MockAvailability() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    static CampgroundAvailability get(final long campgroundId, final LocalDate start, final LocalDate end,
                                             final long... campsiteIds) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(campgroundId);
        availability.setCampsites(stream(campsiteIds)
                .mapToObj(campsiteId -> mock(campsiteId, start, end))
                .collect(toList()));
        return availability;
    }

    private static CampsiteAvailability mock(final long campsiteId, final LocalDate start, final LocalDate end) {
        final CampsiteAvailability siteAvailability = new CampsiteAvailability();
        siteAvailability.setId(campsiteId);
        siteAvailability.setAvailability(randomAvailability(start, end));
        return siteAvailability;
    }

    private static List<DateAvailability> randomAvailability(final LocalDate start, final LocalDate end) {
        return start.datesUntil(end)
                .map(MockAvailability::randomAvailability)
                .collect(toList());
    }

    private static DateAvailability randomAvailability(final LocalDate date) {
        final DateAvailability period = new DateAvailability();
        period.setDate(date);
        period.setStatus(getAvailability(date));
        return period;
    }

    private static Availability getAvailability(final LocalDate date) {
        final LocalDate today = LocalDate.now();
        if (date.isBefore(today)) {
            return Availability.RESERVED;
        } else if (date.isBefore(today.plusDays(3))) {
            return Availability.FIRST_COME_FIRST_SERVE;
        } else if (date.isBefore(today.plusDays(8))) {
            return Availability.RESERVED;
        } else {
            return Availability.AVAILABLE;
        }
    }
}
