package net.seabears.campsites.api.db.mock;

import net.seabears.campsites.db.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockCampgroundData extends AbstractMockDataLoader<Campground> {
    public MockCampgroundData() {
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

    @Override
    public int getOrder() {
        return DataLoadingPriority.CAMPGROUND.priority();
    }
}
