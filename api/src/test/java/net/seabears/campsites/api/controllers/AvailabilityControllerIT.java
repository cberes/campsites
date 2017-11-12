package net.seabears.campsites.api.controllers;

import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.domain.DateAvailability;
import net.seabears.campsites.enums.Availability;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

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
        final UUID id = UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508");
        final String url = base + "/api/availability/campsite/{0}?start={1}&end={2}";
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        final ResponseEntity<CampgroundAvailability> response = template.getForEntity(url, CampgroundAvailability.class,
                id, start, end);
        final CampgroundAvailability availability = response.getBody();
        assertThat(availability.getCampgroundId(), equalTo(UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96")));
        assertThat(availability.getCampsites().get(0).getId(), equalTo(id));
        for (int day = 0; day < days; ++day) {
            final DateAvailability dateAvailability = availability.getCampsites().get(0).getAvailability().get(day);
            MatcherAssert.assertThat(dateAvailability.getStatus(), notNullValue(Availability.class));
            assertThat(dateAvailability.getDate(), equalTo(start.plusDays(day)));
        }
    }

    @ParameterizedTest
    @CsvSource({"area, 0f20c7ef-c2cc-4431-85c0-74977fa2de63", "campground, 9cfa88ec-803d-4f22-83b5-af301af9ca96"})
    public void getCampsites(final String resource, final String uuid) throws Exception {
        final UUID id = UUID.fromString(uuid);
        final String url = base + "/api/availability/{0}/{1}?start={2}&end={3}";
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        final ResponseEntity<CampgroundAvailability> response = template.getForEntity(url, CampgroundAvailability.class,
                resource, id, start, end);
        final CampgroundAvailability availability = response.getBody();
        assertThat(availability.getCampgroundId(), equalTo(id));
        for (int i = 0; i < 2; i++) {
            assertThat(availability.getCampsites().get(i).getId(), equalTo(getCampsiteId(i)));
            for (int day = 0; day < days; ++day) {
                final DateAvailability dateAvailability = availability.getCampsites().get(i).getAvailability().get(day);
                assertThat(dateAvailability.getStatus(), notNullValue(Availability.class));
                assertThat(dateAvailability.getDate(), equalTo(start.plusDays(day)));
            }
        }
    }

    private static UUID getCampsiteId(final int index) {
        if (index == 0) {
            return UUID.fromString("084bfb46-21cb-4c8c-8a9a-3d0d67002d28");
        } else {
            return UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508");
        }
    }
}