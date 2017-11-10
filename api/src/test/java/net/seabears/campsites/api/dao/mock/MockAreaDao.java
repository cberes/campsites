package net.seabears.campsites.api.dao.mock;

import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Repository
public class MockAreaDao extends InMemoryCrudRepository<Area, UUID> implements AreaDao {
    public MockAreaDao() {
        super(List.of(
                buildArea("9cfa88ec-803d-4f22-83b5-af301af9ca96", "Campground X", "Entire campground"),
                buildArea("87767b0f-1ea1-4334-ae70-b7b18a33f5d1", "Campground Y", "Entire campground")));
    }

    private static Area buildArea(final String id, final String name, final String description) {
        return buildArea(id, name, description, id);
    }

    private static Area buildArea(final String id, final String name,
                                  final String description, final String campgroundId) {
        final Campground campground = new Campground();
        campground.setId(UUID.fromString(campgroundId));
        final Area item = new Area();
        item.setId(UUID.fromString(id));
        item.setCampground(campground);
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    @Override
    protected BiConsumer<Area, UUID> idSetter() {
        return Area::setId;
    }

    @Override
    protected Function<Area, UUID> idGetter() {
        return Area::getId;
    }

    @Override
    protected UUID newId() {
        return UUID.randomUUID();
    }

    @Override
    public Iterable<Area> findByCampgroundId(final UUID id) {
        return super.findAll(area -> area.getCampground().getId(), id);
    }
}
