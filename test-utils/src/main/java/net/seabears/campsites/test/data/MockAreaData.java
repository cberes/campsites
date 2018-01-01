package net.seabears.campsites.test.data;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;

import java.util.List;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.toList;

public final class MockAreaData {
    private MockAreaData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Area> load(final UnaryOperator<Area> persist, final List<Campground> campgrounds) {
        return campgrounds.stream()
                .map(campground -> buildArea(campground, "Entire campground"))
                .map(persist)
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
