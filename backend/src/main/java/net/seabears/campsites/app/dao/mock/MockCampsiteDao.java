package net.seabears.campsites.app.dao.mock;

import net.seabears.campsites.app.domain.Campsite;
import net.seabears.campsites.app.dao.CampsiteDao;
import net.seabears.campsites.enums.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@Repository
public class MockCampsiteDao extends MockDao<Campsite> implements CampsiteDao {
    static class CampsiteBuilder {
        private final Campsite campsite = new Campsite();

        Campsite build() {
            return campsite;
        }

        CampsiteBuilder withCampgroundId(final String campgroundId) {
            campsite.setCampgroundId(campgroundId);
            return this;
        }

        CampsiteBuilder withAreaId(final String areaId) {
            campsite.setAreaId(areaId);
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

    public MockCampsiteDao() {
        super(List.of(
                new CampsiteBuilder()
                        .withCampgroundId("1")
                        .withAreaId("1")
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
                        .withCampgroundId("1")
                        .withAreaId("1")
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

    @PostConstruct
    private void init() {
        assignIds();
    }

    @Override
    protected BiConsumer<Campsite, String> getIdSetter() {
        return Campsite::setId;
    }

    @Override
    public List<Campsite> findAllInCampground(final String id) {
        return super.findWith(Campsite::getCampgroundId, id);
    }

    @Override
    public List<Campsite> findAllInArea(final String id) {
        return super.findWith(Campsite::getAreaId, id);
    }

    @Override
    public Optional<Campsite> find(final String id) {
        return super.find(Integer.parseInt(id) - 1);
    }
}
