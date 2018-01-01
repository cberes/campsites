package net.seabears.campsites.test.data;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.enums.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public final class MockCampsiteData {
    private static final int A = (int) 'A';

    private MockCampsiteData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Campsite> load(final UnaryOperator<Campsite> persist, final List<Area> areas) {
        final List<Campsite> campsites = new ArrayList<>(areas.size() * 2);
        for (int i = 0; i < areas.size(); ++i) {
            campsites.add(persist.apply(campsite1(areas.get(i), i)));
            campsites.add(persist.apply(campsite2(areas.get(i), i)));
        }
        return campsites;
    }

    private static Campsite campsite1(final Area area, final int offset) {
        return new Campsite.Builder()
                .withCampground(area.getCampground())
                .withArea(area)
                .withName("Site " + (char) (A + offset))
                .withDescription("Located at the top of Mt. Very High, where the air is very thin.")
                .withNotes(null)
                .withType(SiteType.CAMPSITE)
                .withAccess(Access.DRIVE_IN)
                .withSize(20)
                .withMaxOccupancy(6)
                .withMaxVehicles(1)
                .withPetsAllowed(2)
                .withElectric(Electric.UNKNOWN_AMP)
                .withWater(Water.YES)
                .withSewer(Sewer.NO)
                .build();
    }

    private static Campsite campsite2(final Area area, final int offset) {
        return new Campsite.Builder()
                .withCampground(area.getCampground())
                .withArea(area)
                .withName("Site " + (char) (A + offset + 1))
                .withDescription("Offers beautiful views of the lakeshore cliffs. Stay dry!")
                .withNotes("Infested by ants")
                .withType(SiteType.CAMPSITE)
                .withAccess(Access.DRIVE_IN)
                .withSize(40)
                .withMaxOccupancy(6)
                .withMaxVehicles(2)
                .withPetsAllowed(2)
                .withElectric(Electric.UNKNOWN_AMP)
                .withWater(Water.YES)
                .withSewer(Sewer.NO)
                .build();
    }
}
