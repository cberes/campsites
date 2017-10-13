package net.seabears.campsites.app.service.mock;

import net.seabears.campsites.app.dao.AreaDao;
import net.seabears.campsites.app.dao.CampsiteDao;
import net.seabears.campsites.app.domain.*;
import net.seabears.campsites.app.service.AvailabilityService;
import net.seabears.campsites.enums.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Service
public class MockAvailabilityService implements AvailabilityService {
    private final AreaDao areaDao;
    private final CampsiteDao campsiteDao;

    @Autowired
    public MockAvailabilityService(final AreaDao areaDao, final CampsiteDao campsiteDao) {
        this.areaDao = areaDao;
        this.campsiteDao = campsiteDao;
    }

    @Override
    public CampgroundAvailability findAvailabilityForCampground(final String id, final LocalDate start, final LocalDate end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(id);
        availability.setCampsites(campsiteDao.findByCampgroundId(id).stream()
                .map(campsite -> mockAvailability(campsite, start, end))
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
    public CampgroundAvailability findAvailabilityForArea(final String id, final LocalDate start, final LocalDate end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(areaDao.find(id).map(Area::getCampgroundId).orElse(""));
        availability.setCampsites(campsiteDao.findByAreaId(id).stream()
                .map(campsite -> mockAvailability(campsite, start, end))
                .collect(toList()));
        return availability;
    }

    @Override
    public CampgroundAvailability findAvailabilityForCampsite(final String id, final LocalDate start, final LocalDate end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        campsiteDao.find(id).ifPresent(campsite -> {
            availability.setCampgroundId(campsite.getCampgroundId());
            availability.setCampsites(singletonList(mockAvailability(campsite, start, end)));
        });
        return availability;
    }

    private static List<DateAvailability> randomAvailability(final LocalDate start, final LocalDate end) {
        return start.datesUntil(end)
                .map(MockAvailabilityService::randomAvailability)
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
