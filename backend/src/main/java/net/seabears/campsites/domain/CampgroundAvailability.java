package net.seabears.campsites.domain;

import java.util.List;

public class CampgroundAvailability {
    private String campgroundId;
    private List<CampsiteAvailability> campsites;

    public String getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(final String campgroundId) {
        this.campgroundId = campgroundId;
    }

    public List<CampsiteAvailability> getCampsites() {
        return campsites;
    }

    public void setCampsites(final List<CampsiteAvailability> campsites) {
        this.campsites = campsites;
    }
}
