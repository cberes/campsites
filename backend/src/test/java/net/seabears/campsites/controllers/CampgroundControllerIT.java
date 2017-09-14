package net.seabears.campsites.controllers;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.seabears.campsites.domain.Campground;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

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
    public void getCampground() throws Exception {
        ResponseEntity<Campground> response = template.getForEntity(base + "/api/campground/1", Campground.class);
        assertThat(response.getBody().getId(), equalTo("1"));
        assertThat(response.getBody().getName(), equalTo("Campground Y"));
        assertThat(response.getBody().getDescription(), not(isEmptyOrNullString()));
    }

    @Test
    public void getCampgrounds() throws Exception {
        ResponseEntity<Campground[]> response = template.getForEntity(base + "/api/campground", Campground[].class);
        assertThat(response.getBody()[0].getId(), equalTo("0"));
        assertThat(response.getBody()[0].getName(), equalTo("Campground X"));
        assertThat(response.getBody()[0].getDescription(), not(isEmptyOrNullString()));
        assertThat(response.getBody()[1].getId(), equalTo("1"));
        assertThat(response.getBody()[1].getName(), equalTo("Campground Y"));
        assertThat(response.getBody()[1].getDescription(), not(isEmptyOrNullString()));
    }
}