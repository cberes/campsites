package net.seabears.campsites.db.domain;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Area {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campground_id", nullable = false)
    private Campground campground;

    @Column(columnDefinition = "boolean not null default TRUE")
    private boolean active = true;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 1024, nullable = false)
    private String description;

    @OneToMany(mappedBy = "area")
    private Set<Campsite> campsites;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Campground getCampground() {
        return campground;
    }

    public void setCampground(final Campground campground) {
        this.campground = campground;
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

    public Set<Campsite> getCampsites() {
        return campsites;
    }

    public void setCampsites(final Set<Campsite> campsites) {
        this.campsites = campsites;
    }
}
