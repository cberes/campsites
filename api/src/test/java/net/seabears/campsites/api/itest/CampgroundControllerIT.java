package net.seabears.campsites.api.itest;

import java.net.URL;

import net.seabears.campsites.api.data.MockCampgroundData;
import net.seabears.campsites.be.dao.CampgroundDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
public class CampgroundControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private CampgroundDao dao;

    private boolean filledDatabase;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
        if (!filledDatabase) {
            filledDatabase = true;
            MockCampgroundData.allData().stream().peek(c -> c.setId(0L)).forEach(dao::save);
        }
    }

    @Test
    public void getAreas() {
        final long id = 1L;
        final String url = base + "/api/campgrounds/{id}/areas";
        ResponseEntity<Area[]> response = template.getForEntity(url, Area[].class, id);
        assertThat(response.getBody()[0].getId(), equalTo(1L));
        assertThat(response.getBody()[0].getCampground().getId(), equalTo(id));
        assertThat(response.getBody()[0].getName(), equalTo("Campground X"));
        assertThat(response.getBody()[0].getDescription(), equalTo("Entire campground"));
    }

    @Test
    public void getCampground() {
        final long id = 2L;
        final String url = base + "/api/campgrounds/{0}";
        ResponseEntity<Campground> response = template.getForEntity(url, Campground.class, id);
        assertThat(response.getBody().getId(), equalTo(id));
        assertThat(response.getBody().getName(), equalTo("Campground Y"));
        assertThat(response.getBody().getDescription(), notNullValue(String.class));
    }

    @Test
    public void getCampgrounds() {
        ResponseEntity<Campground[]> response = template.getForEntity(base + "/api/campgrounds", Campground[].class);
        assertThat(response.getBody()[0].getId(), equalTo(1L));
        assertThat(response.getBody()[0].getName(), equalTo("Campground X"));
        assertThat(response.getBody()[0].getDescription(), notNullValue(String.class));
        assertThat(response.getBody()[1].getId(), equalTo(2L));
        assertThat(response.getBody()[1].getName(), equalTo("Campground Y"));
        assertThat(response.getBody()[1].getDescription(), notNullValue(String.class));
    }

    @Test
    public void getCampsites() {
        final long id = 1L;
        final String url = base + "/api/campgrounds/{id}/campsites";
        ResponseEntity<Campsite[]> response = template.getForEntity(url, Campsite[].class, id);
        assertThat(response.getBody()[0].getId(), equalTo(1L));
        assertThat(response.getBody()[0].getCampground().getId(), equalTo(id));
        assertThat(response.getBody()[0].getName(), equalTo("Site A"));
        assertThat(response.getBody()[0].getDescription(), notNullValue(String.class));
        assertThat(response.getBody()[1].getId(), equalTo(2L));
        assertThat(response.getBody()[1].getCampground().getId(), equalTo(id));
        assertThat(response.getBody()[1].getName(), equalTo("Site B"));
        assertThat(response.getBody()[1].getDescription(), notNullValue(String.class));
    }
}