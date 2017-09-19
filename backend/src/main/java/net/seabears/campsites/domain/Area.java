package net.seabears.campsites.domain;

public class Area {
    private String id;
    private String campgroundId;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(final String campgroundId) {
        this.campgroundId = campgroundId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
