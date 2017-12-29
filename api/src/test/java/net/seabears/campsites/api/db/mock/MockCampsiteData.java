package net.seabears.campsites.api.db.mock;

import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import net.seabears.campsites.enums.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockCampsiteData extends AbstractMockDataLoader<Campsite> {
    static class CampsiteBuilder {
        private final Campsite campsite = new Campsite();

        Campsite build() {
            return campsite;
        }

        CampsiteBuilder withId(final long id) {
            campsite.setId(id);
            return this;
        }

        CampsiteBuilder withCampgroundId(final long campgroundId) {
            final Campground campground = new Campground();
            campground.setId(campgroundId);
            campsite.setCampground(campground);
            return this;
        }

        CampsiteBuilder withAreaId(final long areaId) {
            final Area area = new Area();
            area.setId(areaId);
            campsite.setArea(area);
            return this;
        }

        CampsiteBuilder withName(final String name) {
            campsite.setName(name);
            return this;
        }

        CampsiteBuilder withDescription(final String description) {
            campsite.setDescription(description);
            return this;
        }

        CampsiteBuilder withNotes(final String notes) {
            campsite.setNotes(notes);
            return this;
        }

        CampsiteBuilder withType(final SiteType type) {
            campsite.setType(type);
            return this;
        }

        CampsiteBuilder withAccess(final Access access) {
            campsite.setAccess(access);
            return this;
        }

        CampsiteBuilder withSize(final int size) {
            campsite.setSize(size);
            return this;
        }

        CampsiteBuilder withMaxOccupancy(final int maxOccupancy) {
            campsite.setMaxOccupancy(maxOccupancy);
            return this;
        }

        CampsiteBuilder withMaxVehicles(final int maxVehicles) {
            campsite.setMaxVehicles(maxVehicles);
            return this;
        }

        CampsiteBuilder withPetsAllowed(final int petsAllowed) {
            campsite.setPetsAllowed(petsAllowed);
            return this;
        }

        CampsiteBuilder withElectric(final Electric electric) {
            campsite.setElectric(electric);
            return this;
        }

        CampsiteBuilder withWater(final Water water) {
            campsite.setWater(water);
            return this;
        }

        CampsiteBuilder withSewer(final Sewer sewer) {
            campsite.setSewer(sewer);
            return this;
        }
    }

    public MockCampsiteData() {
        super(List.of(
                new CampsiteBuilder()
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
                new CampsiteBuilder()
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
                        .build()));
    }

    @Override
    public int getOrder() {
        return DataLoadingPriority.CAMPSITE.priority();
    }
}
