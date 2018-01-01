package net.seabears.campsites.api.itest;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
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
    public void getArea() {
        ResponseEntity<Area> response = template.getForEntity(base + "/api/areas/{id}", Area.class, 1L);
        assertThat(response.getBody().getId(), equalTo(1L));
        assertThat(response.getBody().getCampground().getId(), equalTo(1L));
        assertThat(response.getBody().getName(), equalTo("Campground X"));
        assertThat(response.getBody().getDescription(), equalTo("Entire campground"));
    }

    @Test
    public void getCampsites() {
        final String url = base + "/api/areas/{id}/campsites";
        ResponseEntity<Campsite[]> response = template.getForEntity(url, Campsite[].class, 1L);

        Campsite campsite = response.getBody()[0];
        assertThat(campsite.getId(), equalTo(1L));
        assertThat(campsite.getCampground().getId(), equalTo(1L));
        assertThat(campsite.getName(), equalTo("Site A"));
        assertThat(campsite.getDescription(), notNullValue(String.class));

        campsite = response.getBody()[1];
        assertThat(campsite.getId(), equalTo(2L));
        assertThat(campsite.getCampground().getId(), equalTo(1L));
        assertThat(campsite.getName(), equalTo("Site B"));
        assertThat(campsite.getDescription(), notNullValue(String.class));
    }
}