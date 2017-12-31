package net.seabears.campsites.be.data;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static java.util.stream.Collectors.toList;

public final class MockAreaData {
    private MockAreaData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Area> load(final TestEntityManager em, final List<Campground> campgrounds) {
        return campgrounds.stream()
                .map(campground -> buildArea(campground, "Entire campground"))
                .map(em::persist)
                .collect(toList());
    }

    private static Area buildArea(final Campground campground, final String description) {
        final Area item = new Area();
        item.setCampground(campground);
        item.setName(campground.getName());
        item.setDescription(description);
        return item;
    }
}
