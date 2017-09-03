package net.seabears.campsites.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import net.seabears.campsites.dao.CampgroundDao;
import net.seabears.campsites.domain.Campground;

import static java.util.stream.IntStream.range;

@Repository
public class MockCampgroundDao implements CampgroundDao {
    private static final List<Campground> ITEMS;

    static {
        ITEMS = List.of(
                buildCampground("Campground X",
                        "Campground X is a really fun place. It lets you get away from the toil of "
                        + "everyday life. The toil of everyday life where too many things at work "
                        + "are misspelled. Located in the deepest, darkest depths of scenic Lake "
                        + "Ontario."),
                buildCampground("Campground Y",
                        "We have the best campsites at Campground Y. Reallly tremendous campsites. "
                        + "And it's surrounded by a 'uge wall that our neighbors paid for. It's "
                        + "completely surrounded. A tremendous, impenetrable wall."));
        assignIds(ITEMS);
    }

    private static Campground buildCampground(final String name, final String description) {
        final Campground item = new Campground();
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    private static void assignIds(final List<Campground> items) {
        range(0, items.size()).forEach(i -> items.get(i).setId(Integer.toString(i)));
    }

    @Override
    public List<Campground> findAll() {
        return ITEMS;
    }

    @Override
    public Optional<Campground> find(final String id) {
        final int index = Integer.parseInt(id);
        if (index >= 0 && index < ITEMS.size()) {
            return Optional.of(ITEMS.get(index));
        } else {
            return Optional.empty();
        }
    }

}
