package net.seabears.campsites.controllers;

import net.seabears.campsites.domain.Area;
import net.seabears.campsites.domain.Campsite;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        ResponseEntity<Area> response = template.getForEntity(base + "/api/area/1", Area.class);
        assertThat(response.getBody().getId(), equalTo("1"));
        assertThat(response.getBody().getCampgroundId(), equalTo("1"));
        assertThat(response.getBody().getName(), equalTo("Campground X"));
        assertThat(response.getBody().getDescription(), equalTo("Entire campground"));
    }

    @Test
    public void getCampsites() throws Exception {
        ResponseEntity<Campsite[]> response = template.getForEntity(base + "/api/area/1/campsites", Campsite[].class);
        assertThat(response.getBody()[0].getId(), equalTo("1"));
        assertThat(response.getBody()[0].getCampgroundId(), equalTo("1"));
        assertThat(response.getBody()[0].getName(), equalTo("Site A"));
        assertThat(response.getBody()[0].getDescription(), not(isEmptyOrNullString()));
        assertThat(response.getBody()[1].getId(), equalTo("2"));
        assertThat(response.getBody()[1].getCampgroundId(), equalTo("1"));
        assertThat(response.getBody()[1].getName(), equalTo("Site B"));
        assertThat(response.getBody()[1].getDescription(), not(isEmptyOrNullString()));
    }
}