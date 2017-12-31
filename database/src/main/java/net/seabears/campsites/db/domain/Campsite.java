package net.seabears.campsites.db.domain;

import javax.persistence.*;

import net.seabears.campsites.enums.*;
import net.seabears.campsites.enums.Access;

import java.util.Set;

@Entity
@Table(name = "campsite")
public class Campsite {
    public static class Builder {
        private final Campsite campsite = new Campsite();

        public Campsite build() {
            return campsite;
        }

        public Builder withId(final long id) {
            campsite.setId(id);
            return this;
        }

        public Builder withCampgroundId(final long campgroundId) {
            final Campground campground = new Campground();
            campground.setId(campgroundId);
            return withCampground(campground);
        }

        public Builder withCampground(final Campground campground) {
            campsite.setCampground(campground);
            return this;
        }

        public Builder withAreaId(final long areaId) {
            final Area area = new Area();
            area.setId(areaId);
            return withArea(area);
        }

        public Builder withArea(final Area area) {
            campsite.setArea(area);
            return this;
        }

        public Builder withName(final String name) {
            campsite.setName(name);
            return this;
        }

        public Builder withDescription(final String description) {
            campsite.setDescription(description);
            return this;
        }

        public Builder withNotes(final String notes) {
            campsite.setNotes(notes);
            return this;
        }

        public Builder withType(final SiteType type) {
            campsite.setType(type);
            return this;
        }

        public Builder withAccess(final Access access) {
            campsite.setAccess(access);
            return this;
        }

        public Builder withSize(final int size) {
            campsite.setSize(size);
            return this;
        }

        public Builder withMaxOccupancy(final int maxOccupancy) {
            campsite.setMaxOccupancy(maxOccupancy);
            return this;
        }

        public Builder withMaxVehicles(final int maxVehicles) {
            campsite.setMaxVehicles(maxVehicles);
            return this;
        }

        public Builder withPetsAllowed(final int petsAllowed) {
            campsite.setPetsAllowed(petsAllowed);
            return this;
        }

        public Builder withElectric(final Electric electric) {
            campsite.setElectric(electric);
            return this;
        }

        public Builder withWater(final Water water) {
            campsite.setWater(water);
            return this;
        }

        public Builder withSewer(final Sewer sewer) {
            campsite.setSewer(sewer);
            return this;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campground_id", nullable = false)
    private Campground campground;

    @Column(columnDefinition = "boolean not null default TRUE")
    private boolean active = true;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(length = 255)
    private String notes;

    @Column
    private SiteType type;

    @Column
    private Access access;

    @Column
    private int size;

    @Column(name = "max_occupancy")
    private int maxOccupancy;

    @Column(name = "max_vehicles")
    private int maxVehicles;

    @Column(name = "pets_allowed")
    private int petsAllowed;

    @Column
    private Electric electric;

    @Column
    private Water water;

    @Column
    private Sewer sewer;

    @OneToMany(mappedBy = "campsite")
    private Set<Reservation> reservations;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(final Area area) {
        this.area = area;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public SiteType getType() {
        return type;
    }

    public void setType(final SiteType type) {
        this.type = type;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(final Access access) {
        this.access = access;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(final int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public int getMaxVehicles() {
        return maxVehicles;
    }

    public void setMaxVehicles(final int maxVehicles) {
        this.maxVehicles = maxVehicles;
    }

    public int getPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(final int petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Electric getElectric() {
        return electric;
    }

    public void setElectric(final Electric electric) {
        this.electric = electric;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(final Water water) {
        this.water = water;
    }

    public Sewer getSewer() {
        return sewer;
    }

    public void setSewer(final Sewer sewer) {
        this.sewer = sewer;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(final Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
