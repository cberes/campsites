package net.seabears.campsites.be.data;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.enums.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public final class MockCampsiteData {
    private static final int A = (int) 'A';

    private MockCampsiteData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Campsite> load(final TestEntityManager em, final List<Area> areas) {
        final List<Campsite> campsites = new ArrayList<>(areas.size() * 2);
        for (int i = 0; i < areas.size(); ++i) {
            campsites.add(campsite1(em, areas.get(i), i));
            campsites.add(campsite2(em, areas.get(i), i));
        }
        return campsites;
    }

    private static Campsite campsite1(final TestEntityManager em, final Area area, final int offset) {
        return em.persist(new Campsite.Builder()
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
                .build());
    }

    private static Campsite campsite2(final TestEntityManager em, final Area area, final int offset) {
        return em.persist(new Campsite.Builder()
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
                .build());
    }
}
