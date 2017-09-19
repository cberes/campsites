package net.seabears.campsites.service.mock;

import net.seabears.campsites.dao.CampsiteDao;
import net.seabears.campsites.domain.*;
import net.seabears.campsites.domain.enums.Availability;
import net.seabears.campsites.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Service
public class MockAvailabilityService implements AvailabilityService {
    private final CampsiteDao dao;

    @Autowired
    public MockAvailabilityService(final CampsiteDao dao) {
        this.dao = dao;
    }

    @Override
    public CampgroundAvailability findAvailabilityForCampground(final String id, final Date start, final Date end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(id);
        availability.setCampsites(dao.findAllInCampground(id).stream()
                .map(campsite -> mockAvailability(campsite, toLocalDate(start), toLocalDate(end)))
                .collect(toList()));
        return availability;
    }

    private static CampsiteAvailability mockAvailability(final Campsite campsite, final LocalDate start, final LocalDate end) {
        final CampsiteAvailability siteAvailability = new CampsiteAvailability();
        siteAvailability.setId(campsite.getId());
        siteAvailability.setAvailability(randomAvailability(start, end));
        return siteAvailability;
    }

    @Override
    public CampgroundAvailability findAvailabilityForArea(final String id, final Date start, final Date end) {
        // assume 1 area per campground with the same ID
        return findAvailabilityForCampground(id, start, end);
    }

    @Override
    public CampgroundAvailability findAvailabilityForCampsite(final String id, final Date start, final Date end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        dao.find(id).ifPresent(campsite -> {
            availability.setCampgroundId(campsite.getCampgroundId());
            availability.setCampsites(singletonList(mockAvailability(campsite, toLocalDate(start), toLocalDate(end))));
        });
        return availability;
    }

    private static LocalDate toLocalDate(final Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static List<DateAvailability> randomAvailability(final LocalDate start, final LocalDate end) {
        return start.datesUntil(end)
                .map(MockAvailabilityService::randomAvailability)
                .collect(toList());
    }

    private static DateAvailability randomAvailability(final LocalDate date) {
        final DateAvailability period = new DateAvailability();
        period.setDate(date);
        period.setAvailability(random(Availability.class));
        return period;
    }

    private static <T extends Enum<T>> T random(final Class<T> type) {
        final int min = 0;
        final T[] values = type.getEnumConstants();
        final int index = ThreadLocalRandom.current().ints(min, values.length).findFirst().orElse(min);
        return values[index];
    }
}
