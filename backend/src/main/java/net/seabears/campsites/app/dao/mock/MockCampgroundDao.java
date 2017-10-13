package net.seabears.campsites.app.dao.mock;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import net.seabears.campsites.app.dao.CampgroundDao;
import org.springframework.stereotype.Repository;

import net.seabears.campsites.app.domain.Campground;

import javax.annotation.PostConstruct;

@Repository
public class MockCampgroundDao extends MockDao<Campground> implements CampgroundDao {
    public MockCampgroundDao() {
        super(List.of(
                buildCampground("Campground X",
                        "Campground X is a really fun place. It lets you get away from the toil of "
                        + "everyday life. The toil of everyday life where too many things at work "
                        + "are misspelled. Located in the deepest, darkest depths of scenic Lake "
                        + "Ontario."),
                buildCampground("Campground Y",
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

    @PostConstruct
    private void init() {
        assignIds();
    }

    @Override
    protected BiConsumer<Campground, String> getIdSetter() {
        return Campground::setId;
    }

    @Override
    public List<Campground> findAll() {
        return allItems();
    }

    @Override
    public Optional<Campground> find(final String id) {
        return super.find(Integer.parseInt(id) - 1);
    }
}
