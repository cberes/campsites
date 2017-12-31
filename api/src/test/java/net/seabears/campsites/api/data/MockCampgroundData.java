package net.seabears.campsites.api.data;

import net.seabears.campsites.db.domain.Campground;

import java.util.List;
import java.util.Optional;

public final class MockCampgroundData {
    private MockCampgroundData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Campground> allData() {
        return List.of(
                buildCampground(1, "Campground X",
                        "Campground X is a really fun place. It lets you get away from the toil of "
                                + "everyday life. The toil of everyday life where too many things at work "
                                + "are misspelled. Located in the deepest, darkest depths of scenic Lake "
                                + "Ontario."),
                buildCampground(2, "Campground Y",
                        "We have the best campsites at Campground Y. Reallly tremendous campsites. "
                                + "And it's surrounded by a 'uge wall that our neighbors paid for. It's "
                                + "completely surrounded. A tremendous, impenetrable wall."));
    }

    private static Campground buildCampground(final int id, final String name, final String description) {
        final Campground item = new Campground();
        item.setId(id);
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    public static Optional<Campground> get(final int i) {
        final List<Campground> data = allData();
        return i < 0 || i >= data.size() ? Optional.empty() : Optional.of(data.get(i));
    }
}
