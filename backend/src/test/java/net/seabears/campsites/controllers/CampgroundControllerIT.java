package net.seabears.campsites.controllers;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import net.seabears.campsites.domain.Campground;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampgroundControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
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