package net.seabears.campsites.be.dao;

import net.seabears.campsites.be.config.TestConfiguration;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.test.data.MockCampgroundData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
@DataJpaTest
public class CampgroundDaoTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private CampgroundDao dao;

    @Test
    public void findByIdShouldReturnCampground() {
        final long id = MockCampgroundData.load(em::persist).get(0).getId();
        Optional<Campground> campground = dao.findById(id);

        assertThat(campground.isPresent(), is(true));
        assertThat(campground.get().getName(), is("Campground X"));
    }
}
