package net.seabears.campsites.be.service.impl;

import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.be.dao.ReservationDao;
import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.service.AvailabilityService;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.enums.Availability;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class DaoAvailabilityService implements AvailabilityService {
    private static final int MIN_DAYS_TO_RESERVE = 2;
    private static final Availability STATUS_TOO_LATE = Availability.IN_PERSON_ONLY;

    private final AreaDao areaDao;
    private final CampsiteDao campsiteDao;
    private final ReservationDao reservationDao;

    public DaoAvailabilityService(final AreaDao areaDao,
                                  final CampsiteDao campsiteDao,
                                  final ReservationDao reservationDao) {
        this.areaDao = areaDao;
        this.campsiteDao = campsiteDao;
        this.reservationDao = reservationDao;
    }

    @Override
    public CampgroundAvailability findByCampgroundId(final long id, final LocalDate start, final LocalDate end) {
        final Iterable<Campsite> campsites = campsiteDao.findByCampgroundId(id);
        final List<Long> campsiteIds = campsiteIds(campsites);
        return find(campsiteIds, start, end, id);
    }

    private static List<Long> campsiteIds(final Iterable<Campsite> campsites) {
        return campsiteIds(StreamSupport.stream(campsites.spliterator(), false));
    }

    private static List<Long> campsiteIds(final Stream<Campsite> campsites) {
        return campsites.map(Campsite::getId).collect(toList());
    }

    @Override
    public CampgroundAvailability findByAreaId(final long id, final LocalDate start, final LocalDate end) {
        final long campgroundId = areaDao.findById(id).map(Area::getCampground).map(Campground::getId).orElse(0L);
        final Iterable<Campsite> campsites = campsiteDao.findByAreaId(id);
        final List<Long> campsiteIds = campsiteIds(campsites);
        return find(campsiteIds, start, end, campgroundId);
    }

    @Override
    public CampgroundAvailability findByCampsiteId(final long id, final LocalDate start, final LocalDate end) {
        final Optional<Campsite> campsite = campsiteDao.findById(id);
        final long campgroundId = campsite.map(Campsite::getCampground).map(Campground::getId).orElse(0L);
        final List<Long> campsiteIds = campsiteIds(campsite.stream());
        return find(campsiteIds, start, end, campgroundId);
    }

    private CampgroundAvailability find(final List<Long> campsiteIds, final LocalDate start, final LocalDate end,
                                        final long campgroundId) {
        final LocalDate minDateToReserve = LocalDate.now().plusDays(MIN_DAYS_TO_RESERVE);
        final AvailabilityFunction func = new AvailabilityFunction(minDateToReserve, STATUS_TOO_LATE);
        return new AvailabilityBuilder(start, end, func).findAvailability(reservationDao, campgroundId, campsiteIds);
    }
}
