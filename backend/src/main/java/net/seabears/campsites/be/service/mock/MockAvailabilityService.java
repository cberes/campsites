package net.seabears.campsites.be.service.mock;

import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.CampsiteAvailability;
import net.seabears.campsites.be.domain.DateAvailability;
import net.seabears.campsites.be.service.AvailabilityService;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.enums.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
    public CampgroundAvailability findByCampgroundId(final long id, final LocalDate start, final LocalDate end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(id);
        availability.setCampsites(stream(campsiteDao.findByCampgroundId(id))
                .map(campsite -> mock(campsite, start, end))
                .collect(toList()));
        return availability;
    }

    private static <T> Stream<T> stream(final Iterable<T> iter) {
        return StreamSupport.stream(iter.spliterator(), false);
    }

    private static CampsiteAvailability mock(final Campsite campsite, final LocalDate start, final LocalDate end) {
        final CampsiteAvailability siteAvailability = new CampsiteAvailability();
        siteAvailability.setId(campsite.getId());
        siteAvailability.setAvailability(randomAvailability(start, end));
        return siteAvailability;
    }

    @Override
    public CampgroundAvailability findByAreaId(final long id, final LocalDate start, final LocalDate end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        availability.setCampgroundId(areaDao.findById(id).map(Area::getCampground).map(Campground::getId).orElse(0L));
        availability.setCampsites(stream(campsiteDao.findByAreaId(id))
                .map(campsite -> mock(campsite, start, end))
                .collect(toList()));
        return availability;
    }

    @Override
    public CampgroundAvailability findByCampsiteId(final long id, final LocalDate start, final LocalDate end) {
        final CampgroundAvailability availability = new CampgroundAvailability();
        campsiteDao.findById(id).ifPresent(campsite -> {
            availability.setCampgroundId(campsite.getCampground().getId());
            availability.setCampsites(singletonList(mock(campsite, start, end)));
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
