package net.seabears.campsites.api.controllers;

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
        final UUID id = UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508");
        ResponseEntity<Campsite> response = template.getForEntity(base + "/api/campsites/{0}", Campsite.class, id);
        assertThat(response.getBody().getId(), equalTo(id));
        assertThat(response.getBody().getCampground().getId(),
                equalTo(UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96")));
        assertThat(response.getBody().getName(), equalTo("Site B"));
        assertThat(response.getBody().getDescription(), notNullValue(String.class));
    }
}