package net.seabears.campsites.be.dao;

import net.seabears.campsites.be.config.TestConfiguration;
import net.seabears.campsites.be.data.MockAreaData;
import net.seabears.campsites.be.data.MockCampgroundData;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
@DataJpaTest
public class AreaDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AreaDao dao;

    @Test
    public void findByIdShouldReturnArea() {
        final long id = MockAreaData.load(entityManager, MockCampgroundData.load(entityManager)).get(0).getId();
        Optional<Area> area = dao.findById(id);

        assertThat(area.isPresent(), is(true));
        assertThat(area.get().getName(), is("Campground X"));
    }

    @Test
    public void findByCampgroundIdShouldReturnAreas() {
        final List<Campground> campgrounds = MockCampgroundData.load(entityManager);
        MockAreaData.load(entityManager, campgrounds);
        Iterable<Area> areas = dao.findByCampgroundId(campgrounds.get(1).getId());

        final Iterator<Area> iter = areas.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getName(), is("Campground Y"));
        assertThat(iter.hasNext(), is(false));
    }
}
