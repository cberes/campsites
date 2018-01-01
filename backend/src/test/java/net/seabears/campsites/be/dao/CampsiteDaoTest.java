package net.seabears.campsites.be.dao;

import net.seabears.campsites.be.config.TestConfiguration;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.test.data.MockAreaData;
import net.seabears.campsites.test.data.MockCampgroundData;
import net.seabears.campsites.test.data.MockCampsiteData;
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
public class CampsiteDaoTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private CampsiteDao dao;

    @Test
    public void findByIdShouldReturnCampsite() {
        final long id = mockData().get(0).getId();
        Optional<Campsite> campsite = dao.findById(id);

        assertThat(campsite.isPresent(), is(true));
        assertThat(campsite.get().getName(), is("Site A"));
    }

    private List<Campsite> mockData() {
        final List<Campground> campgrounds = MockCampgroundData.load(em::persist);
        final List<Area> areas = MockAreaData.load(em::persist, campgrounds);
        return MockCampsiteData.load(em::persist, areas);
    }

    @Test
    public void findByAreaIdShouldReturnCampsites() {
        final long id = mockData().get(0).getArea().getId();
        Iterable<Campsite> campsites = dao.findByAreaId(id);

        final Iterator<Campsite> iter = campsites.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getName(), is("Site A"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getName(), is("Site B"));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void findByCampgroundIdShouldReturnCampsites() {
        final long id = mockData().get(0).getCampground().getId();
        Iterable<Campsite> campsites = dao.findByCampgroundId(id);

        final Iterator<Campsite> iter = campsites.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getName(), is("Site A"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getName(), is("Site B"));
        assertThat(iter.hasNext(), is(false));
    }
}
