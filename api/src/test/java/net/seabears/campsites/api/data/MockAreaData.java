package net.seabears.campsites.api.data;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;

import java.util.List;
import java.util.Optional;

public final class MockAreaData {
    private MockAreaData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Area> allData() {
        return List.of(
                buildArea("Campground X", "Entire campground", 1L),
                buildArea("Campground Y", "Entire campground", 2L));
    }

    private static Area buildArea(final String name, final String description, final long campgroundId) {
        final Area item = new Area();
        item.setId(campgroundId);
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

    public static Optional<Area> get(final int i) {
        final List<Area> data = allData();
        return i < 0 || i >= data.size() ? Optional.empty() : Optional.of(data.get(i));
    }
}
