package net.seabears.campsites.api.data;

import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.enums.*;

import java.util.List;
import java.util.Optional;

public final class MockCampsiteData {
    private MockCampsiteData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Campsite> allData() {
        return List.of(
                new Campsite.Builder()
                        .withId(1L)
                        .withCampgroundId(1L)
                        .withAreaId(1L)
                        .withName("Site A")
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
                        .build(),
                new Campsite.Builder()
                        .withId(2L)
                        .withCampgroundId(1L)
                        .withAreaId(1L)
                        .withName("Site B")
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
                        .build());
    }

    public static Optional<Campsite> get(final int i) {
        final List<Campsite> data = allData();
        return i < 0 || i >= data.size() ? Optional.empty() : Optional.of(data.get(i));
    }
}
