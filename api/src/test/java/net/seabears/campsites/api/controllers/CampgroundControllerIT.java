package net.seabears.campsites.api.controllers;

import java.net.URL;
import java.util.UUID;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampgroundControllerIT {
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
    public void getAreas() throws Exception {
        final UUID id = UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96");
        final String url = base + "/api/campgrounds/{0}/areas";
        ResponseEntity<Area[]> response = template.getForEntity(url, Area[].class, id);
        assertThat(response.getBody()[0].getId(), equalTo(UUID.fromString("0f20c7ef-c2cc-4431-85c0-74977fa2de63")));
        assertThat(response.getBody()[0].getCampground().getId(), equalTo(id));
        assertThat(response.getBody()[0].getName(), equalTo("Campground X"));
        assertThat(response.getBody()[0].getDescription(), equalTo("Entire campground"));
    }

    @Test
    public void getCampground() throws Exception {
        final UUID id = UUID.fromString("87767b0f-1ea1-4334-ae70-b7b18a33f5d1");
        final String url = base + "/api/campgrounds/{0}";
        ResponseEntity<Campground> response = template.getForEntity(url, Campground.class, id);
        assertThat(response.getBody().getId(), equalTo(id));
        assertThat(response.getBody().getName(), equalTo("Campground Y"));
        assertThat(response.getBody().getDescription(), notNullValue(String.class));
    }

    @Test
    public void getCampgrounds() throws Exception {
        ResponseEntity<Campground[]> response = template.getForEntity(base + "/api/campgrounds", Campground[].class);
        assertThat(response.getBody()[0].getId(), equalTo(UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96")));
        assertThat(response.getBody()[0].getName(), equalTo("Campground X"));
        assertThat(response.getBody()[0].getDescription(), notNullValue(String.class));
        assertThat(response.getBody()[1].getId(), equalTo(UUID.fromString("87767b0f-1ea1-4334-ae70-b7b18a33f5d1")));
        assertThat(response.getBody()[1].getName(), equalTo("Campground Y"));
        assertThat(response.getBody()[1].getDescription(), notNullValue(String.class));
    }

    @Test
    public void getCampsites() throws Exception {
        final UUID id = UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96");
        final String url = base + "/api/campgrounds/{0}/campsites";
        ResponseEntity<Campsite[]> response = template.getForEntity(url, Campsite[].class, id);
        assertThat(response.getBody()[0].getId(), equalTo(UUID.fromString("084bfb46-21cb-4c8c-8a9a-3d0d67002d28")));
        assertThat(response.getBody()[0].getCampground().getId(), equalTo(id));
        assertThat(response.getBody()[0].getName(), equalTo("Site A"));
        assertThat(response.getBody()[0].getDescription(), notNullValue(String.class));
        assertThat(response.getBody()[1].getId(), equalTo(UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508")));
        assertThat(response.getBody()[1].getCampground().getId(), equalTo(id));
        assertThat(response.getBody()[1].getName(), equalTo("Site B"));
        assertThat(response.getBody()[1].getDescription(), notNullValue(String.class));
    }
}