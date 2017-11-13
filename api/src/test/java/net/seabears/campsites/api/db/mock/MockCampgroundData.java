package net.seabears.campsites.api.db.mock;

import net.seabears.campsites.db.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MockCampgroundData extends AbstractMockDataLoader<Campground> {
    public MockCampgroundData() {
        super(List.of(
                buildCampground("9cfa88ec-803d-4f22-83b5-af301af9ca96",
                        "Campground X",
                        "Campground X is a really fun place. It lets you get away from the toil of "
                        + "everyday life. The toil of everyday life where too many things at work "
                        + "are misspelled. Located in the deepest, darkest depths of scenic Lake "
                        + "Ontario."),
                buildCampground("87767b0f-1ea1-4334-ae70-b7b18a33f5d1",
                        "Campground Y",
                        "We have the best campsites at Campground Y. Reallly tremendous campsites. "
                        + "And it's surrounded by a 'uge wall that our neighbors paid for. It's "
                        + "completely surrounded. A tremendous, impenetrable wall.")));
    }

    private static Campground buildCampground(final String id, final String name, final String description) {
        final Campground item = new Campground();
        item.setId(UUID.fromString(id));
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    @Override
    public int getOrder() {
        return DataLoadingPriority.CAMPGROUND.priority();
    }
}