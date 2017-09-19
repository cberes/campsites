package net.seabears.campsites.dao.mock;

import net.seabears.campsites.dao.AreaDao;
import net.seabears.campsites.domain.Area;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@Repository
public class MockAreaDao extends MockDao<Area> implements AreaDao {
    public MockAreaDao() {
        super(List.of(
                buildArea("1", "Campground X", "Entire campground"),
                buildArea("2", "Campground Y", "Entire campground")));
    }

    private static Area buildArea(final String campgroundId, final String name, final String description) {
        final Area item = new Area();
        item.setCampgroundId(campgroundId);
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    @PostConstruct
    private void init() {
        assignIds();
    }

    @Override
    protected BiConsumer<Area, String> getIdSetter() {
        return Area::setId;
    }

    @Override
    public List<Area> findAllInCampground(final String id) {
        return super.findWith(Area::getCampgroundId, id);
    }

    @Override
    public Optional<Area> find(final String id) {
        return super.find(Integer.parseInt(id) - 1);
    }
}
