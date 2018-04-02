package net.seabears.campsites.be.service.impl;

import net.seabears.campsites.be.config.ServiceTestConfiguration;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.be.dao.ReservationDao;
import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.CampsiteAvailability;
import net.seabears.campsites.be.service.AvailabilityService;
import net.seabears.campsites.db.domain.*;
import net.seabears.campsites.test.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class DaoAvailabilityServiceTest {
    @Autowired
    private AvailabilityService service;

    @Autowired
    private CampsiteDao campsiteDao;

    @Autowired
    private ReservationDao reservationDao;

    private MockPersistence db;

    @BeforeEach
    public void setup() {
        db = new MockPersistence();
    }

    @Test
    public void testNoReservations() {
        final List<Campsite> campsites = mockCampsites();
        final long id = campsites.get(0).getId();
        when(campsiteDao.findById(id)).thenReturn(campsites.stream().findFirst());
        when(reservationDao.findAll()).thenReturn(emptyList());

        final LocalDate start = LocalDate.of(2018, 4, 1);
        final LocalDate end = LocalDate.of(2018, 4, 10);
        final CampgroundAvailability avail = service.findByCampsiteId(id, start, end.plusDays(1));
        assertThat(avail, notNullValue());
        assertThat(avail.getCampgroundId(), is(campsites.get(0).getCampground().getId()));
        assertThat(avail.getCampsites().size(), is(1));

        final CampsiteAvailability campsite = avail.getCampsites().get(0);
        AvailabilityTester.test(campsite, id, "IIAAAAAAAA");
        AvailabilityTester.assertStartEnd(campsite, start, end);
    }

    private List<Campsite> mockCampsites() {
        final List<Campground> campgrounds = MockCampgroundData.load(db::persist);
        final List<Area> areas = MockAreaData.load(db::persist, campgrounds);
        return MockCampsiteData.load(db::persist, areas);
    }

    @Test
    public void testReservations() {
        final LocalDate start = LocalDate.of(2018, 4, 1);
        final LocalDate end = LocalDate.of(2018, 4, 10);

        final List<Campsite> campsites = mockCampsites().stream().limit(2).collect(toList());
        final long id = campsites.get(0).getCampground().getId();
        when(campsiteDao.findByCampgroundId(id)).thenReturn(campsites);
        mockReservations(campsites, start);

        final CampgroundAvailability avail = service.findByCampgroundId(id, start, end.plusDays(1));
        assertThat(avail, notNullValue());
        assertThat(avail.getCampgroundId(), is(id));
        assertThat(avail.getCampsites().size(), is(2));

        AvailabilityTester.test(avail.getCampsites().get(0), campsites.get(0).getId(), "RRARAAARRA");
        AvailabilityTester.assertStartEnd(avail.getCampsites().get(0), start, end);

        AvailabilityTester.test(avail.getCampsites().get(1), campsites.get(1).getId(), "IIAAAAARRR");
        AvailabilityTester.assertStartEnd(avail.getCampsites().get(1), start, end);
    }

    private void mockReservations(final List<Campsite> campsites, final LocalDate start) {
        final Customer customer = MockCustomerData.load(db::persist).get(0);
        final List<Reservation> reservations = MockReservationData.load(
                db::persist, campsites, customer, start);
        when(reservationDao.findAll()).thenReturn(reservations);
    }
}
