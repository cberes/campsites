package net.seabears.campsites.api.controllers;

import net.seabears.campsites.db.domain.Campsite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CampsiteControllerIT {
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
        final long id = 2L;
        ResponseEntity<Campsite> response = template.getForEntity(base + "/api/campsites/{id}", Campsite.class, id);
        assertThat(response.getBody().getId(), equalTo(id));
        assertThat(response.getBody().getCampground().getId(), equalTo(1L));
        assertThat(response.getBody().getName(), equalTo("Site B"));
        assertThat(response.getBody().getDescription(), notNullValue(String.class));
    }
}