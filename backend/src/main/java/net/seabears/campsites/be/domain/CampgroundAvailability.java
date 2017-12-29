package net.seabears.campsites.be.domain;

import java.util.List;

public class CampgroundAvailability {
    private long campgroundId;
    private List<CampsiteAvailability> campsites;

    public long getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(final long campgroundId) {
        this.campgroundId = campgroundId;
    }

    public List<CampsiteAvailability> getCampsites() {
        return campsites;
    }

    public void setCampsites(final List<CampsiteAvailability> campsites) {
        this.campsites = campsites;
    }
}
