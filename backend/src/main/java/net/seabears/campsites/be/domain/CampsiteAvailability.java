package net.seabears.campsites.be.domain;

import java.util.List;

public class CampsiteAvailability {
    private long id;
    private List<DateAvailability> availability;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public List<DateAvailability> getAvailability() {
        return availability;
    }

    public void setAvailability(final List<DateAvailability> availability) {
        this.availability = availability;
    }
}
