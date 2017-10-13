package net.seabears.campsites.app.domain;

import java.util.List;

public class CampsiteAvailability {
    private String id;
    private List<DateAvailability> availability;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<DateAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(final List<DateAvailability> availability) {
        this.availability = availability;
    }
}
