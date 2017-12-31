package net.seabears.campsites.be.data;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import net.seabears.campsites.db.domain.Campground;

import java.util.List;

public final class MockCampgroundData {
    private MockCampgroundData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Campground> load(final TestEntityManager em) {
        return List.of(
                em.persist(buildCampground("Campground X",
                "Campground X is a really fun place. It lets you get away from the toil of "
                        + "everyday life. The toil of everyday life where too many things at work "
                        + "are misspelled. Located in the deepest, darkest depths of scenic Lake "
                        + "Ontario.")),
                em.persist(buildCampground("Campground Y",
                        "We have the best campsites at Campground Y. Reallly tremendous campsites. "
                        + "And it's surrounded by a 'uge wall that our neighbors paid for. It's "
                        + "completely surrounded. A tremendous, impenetrable wall.")));
    }

    private static Campground buildCampground(final String name, final String description) {
        final Campground item = new Campground();
        item.setName(name);
        item.setDescription(description);
        return item;
    }
}
