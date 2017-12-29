package net.seabears.campsites.api.db.mock;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockAreaData extends AbstractMockDataLoader<Area> {
    public MockAreaData() {
        super(List.of(
                buildArea("Campground X", "Entire campground", 1L),
                buildArea("Campground Y", "Entire campground", 2L)));
    }

    private static Area buildArea(final String name, final String description, final long campgroundId) {
        final Area item = new Area();
        item.setCampground(buildCampground(campgroundId));
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    private static Campground buildCampground(final long id) {
        final Campground campground = new Campground();
        campground.setId(id);
        return campground;
    }

    @Override
    public int getOrder() {
        return DataLoadingPriority.AREA.priority();
    }
}
