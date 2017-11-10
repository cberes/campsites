package net.seabears.campsites.api.controllers;

import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.DateAvailability;
import net.seabears.campsites.enums.Availability;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvailabilityControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

    @Test
    public void getCampsite() throws Exception {
        final String id = "2";
        final String url = base + "/api/availability/campsite/{0}?start={1}&end={2}";
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        final ResponseEntity<CampgroundAvailability> response = template.getForEntity(url, CampgroundAvailability.class,
                id, start, end);
        final CampgroundAvailability availability = response.getBody();
        assertThat(availability.getCampgroundId(), equalTo("1"));
        assertThat(availability.getCampsites().get(0).getId(), equalTo(id));
        for (int day = 0; day < days; ++day) {
            final DateAvailability dateAvailability = availability.getCampsites().get(0).getAvailability().get(day);
            MatcherAssert.assertThat(dateAvailability.getStatus(), notNullValue(Availability.class));
            assertThat(dateAvailability.getDate(), equalTo(start.plusDays(day)));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground"})
    public void getCampsites(final String resource) throws Exception {
        final String id = "1";
        final String url = base + "/api/availability/{0}/{1}?start={2}&end={3}";
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        final ResponseEntity<CampgroundAvailability> response = template.getForEntity(url, CampgroundAvailability.class,
                resource, id, start, end);
        final CampgroundAvailability availability = response.getBody();
        assertThat(availability.getCampgroundId(), equalTo(id));
        for (int i = 0; i < 2; i++) {
            assertThat(availability.getCampsites().get(i).getId(), equalTo(Integer.toString(i + 1)));
            for (int day = 0; day < days; ++day) {
                final DateAvailability dateAvailability = availability.getCampsites().get(i).getAvailability().get(day);
                assertThat(dateAvailability.getStatus(), notNullValue(Availability.class));
                assertThat(dateAvailability.getDate(), equalTo(start.plusDays(day)));
            }
        }
    }
}