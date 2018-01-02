package net.seabears.campsites.api.itest;

import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampgroundDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.DateAvailability;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.enums.Availability;
import net.seabears.campsites.test.data.MockAreaData;
import net.seabears.campsites.test.data.MockCampgroundData;
import net.seabears.campsites.test.data.MockCampsiteData;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
public class AvailabilityControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private CampgroundDao campgroundDao;

    @Autowired
    private CampsiteDao campsiteDao;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
        if (campsiteDao.count() == 0) {
            fillDatabase();
        }
    }

    private void fillDatabase() {
        final List<Campground> campgrounds = MockCampgroundData.load(campgroundDao::save);
        final List<Area> areas = MockAreaData.load(areaDao::save, campgrounds);
        MockCampsiteData.load(campsiteDao::save, areas);
    }

    @Test
    public void getCampsite() {
        final long id = 2L;
        final String url = base + "/api/availability/campsite/{0}?start={1}&end={2}";
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        final ResponseEntity<CampgroundAvailability> response = template.getForEntity(url, CampgroundAvailability.class,
                id, start, end);
        final CampgroundAvailability availability = response.getBody();
        assertThat(availability.getCampgroundId(), equalTo(1L));
        assertThat(availability.getCampsites().get(0).getId(), equalTo(id));
        for (int day = 0; day < days; ++day) {
            final DateAvailability dateAvailability = availability.getCampsites().get(0).getAvailability().get(day);
            MatcherAssert.assertThat(dateAvailability.getStatus(), notNullValue(Availability.class));
            assertThat(dateAvailability.getDate(), equalTo(start.plusDays(day)));
        }
    }

    @ParameterizedTest
    @CsvSource({"area, 1", "campground, 1"})
    public void getCampsites(final String resource, final long id) {
        final String url = base + "/api/availability/{0}/{1}?start={2}&end={3}";
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        final ResponseEntity<CampgroundAvailability> response = template.getForEntity(url, CampgroundAvailability.class,
                resource, id, start, end);
        final CampgroundAvailability availability = response.getBody();
        assertThat(availability.getCampgroundId(), equalTo(id));
        for (int i = 0; i < 2; i++) {
            assertThat(availability.getCampsites().get(i).getId(), equalTo(i + 1L));
            for (int day = 0; day < days; ++day) {
                final DateAvailability dateAvailability = availability.getCampsites().get(i).getAvailability().get(day);
                assertThat(dateAvailability.getStatus(), notNullValue(Availability.class));
                assertThat(dateAvailability.getDate(), equalTo(start.plusDays(day)));
            }
        }
    }
}
