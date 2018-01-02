package net.seabears.campsites.api.itest;

import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampgroundDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.test.data.MockAreaData;
import net.seabears.campsites.test.data.MockCampgroundData;
import net.seabears.campsites.test.data.MockCampsiteData;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
public class CampsiteControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private CampgroundDao campgroundDao;

    @Autowired
    private CampsiteDao campsiteDao;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
        if (campsiteDao.count() == 0) {
            fillDatabase();
        }
    }

    private void fillDatabase() {
        final List<Campground> campgrounds = MockCampgroundData.load(campgroundDao::save);
        final List<Area> areas = MockAreaData.load(areaDao::save, campgrounds);
        MockCampsiteData.load(campsiteDao::save, areas);
    }

    @Test
    public void getCampsite() {
        final long id = 2L;
        ResponseEntity<Campsite> response = template.getForEntity(base + "/api/campsites/{id}", Campsite.class, id);
        assertThat(response.getBody().getId(), equalTo(id));
        assertThat(response.getBody().getCampground().getId(), equalTo(1L));
        assertThat(response.getBody().getName(), equalTo("Site B"));
        assertThat(response.getBody().getDescription(), notNullValue(String.class));
    }
}
