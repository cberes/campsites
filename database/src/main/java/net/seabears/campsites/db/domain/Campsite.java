package net.seabears.campsites.db.domain;

import javax.persistence.*;

import net.seabears.campsites.enums.*;
import net.seabears.campsites.enums.Access;

import java.util.UUID;

@Entity
public class Campsite {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "campground_id", nullable = false)
    private Campground campground;

    @Column(nullable = false)
    private boolean active;

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

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
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
}