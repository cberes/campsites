package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Campground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "boolean not null default TRUE")
    private boolean active = true;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 1024, nullable = false)
    private String description;

    @OneToMany(mappedBy = "campground")
    private Set<Area> areas;

    @OneToMany(mappedBy = "campground")
    private Set<Campsite> campsites;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
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

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(final Set<Area> areas) {
        this.areas = areas;
    }

    public Set<Campsite> getCampsites() {
        return campsites;
    }

    public void setCampsites(final Set<Campsite> campsites) {
        this.campsites = campsites;
    }
}
