package net.seabears.campsites.api.db.mock;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MockAreaData extends AbstractMockDataLoader<Area> {
    public MockAreaData() {
        super(List.of(
                buildArea("0f20c7ef-c2cc-4431-85c0-74977fa2de63", "Campground X", "Entire campground",
                        "9cfa88ec-803d-4f22-83b5-af301af9ca96"),
                buildArea("9883e124-fb34-4cad-8b27-0b5d15d2d718", "Campground Y", "Entire campground",
                        "87767b0f-1ea1-4334-ae70-b7b18a33f5d1")));
    }

    private static Area buildArea(final String id, final String name,
                                  final String description, final String campgroundId) {
        final Area item = new Area();
        item.setId(UUID.fromString(id));
        item.setCampground(buildCampground(campgroundId));
        item.setName(name);
        item.setDescription(description);
        return item;
    }

    private static Campground buildCampground(final String id) {
        final Campground campground = new Campground();
        campground.setId(UUID.fromString(id));
        return campground;
    }

    @Override
    public int getOrder() {
        return DataLoadingPriority.AREA.priority();
    }
}
