package net.seabears.campsites.api.controllers;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AreaControllerIT {
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
    public void getArea() throws Exception {
        final UUID id = UUID.fromString("0f20c7ef-c2cc-4431-85c0-74977fa2de63");
        final UUID campgroundId = UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96");
        ResponseEntity<Area> response = template.getForEntity(base + "/api/areas/{0}", Area.class, id);
        assertThat(response.getBody().getId(), equalTo(id));
        assertThat(response.getBody().getCampground().getId(), equalTo(campgroundId));
        assertThat(response.getBody().getName(), equalTo("Campground X"));
        assertThat(response.getBody().getDescription(), equalTo("Entire campground"));
    }

    @Test
    public void getCampsites() throws Exception {
        final String url = base + "/api/areas/0f20c7ef-c2cc-4431-85c0-74977fa2de63/campsites";
        final UUID campgroundId = UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96");
        ResponseEntity<Campsite[]> response = template.getForEntity(url, Campsite[].class);

        Campsite campsite = response.getBody()[0];
        assertThat(campsite.getId(), equalTo(UUID.fromString("084bfb46-21cb-4c8c-8a9a-3d0d67002d28")));
        assertThat(campsite.getCampground().getId(), equalTo(campgroundId));
        assertThat(campsite.getName(), equalTo("Site A"));
        assertThat(campsite.getDescription(), notNullValue(String.class));

        campsite = response.getBody()[1];
        assertThat(campsite.getId(), equalTo(UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508")));
        assertThat(campsite.getCampground().getId(), equalTo(campgroundId));
        assertThat(campsite.getName(), equalTo("Site B"));
        assertThat(campsite.getDescription(), notNullValue(String.class));
    }
}