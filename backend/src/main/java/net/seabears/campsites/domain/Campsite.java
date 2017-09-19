package net.seabears.campsites.domain;

import net.seabears.campsites.domain.enums.*;

public class Campsite {
    private String id;
    private String campgroundId;
    private String areaId;
    private String name;
    private String description;
    private String notes;
    private SiteType type;
    private Access access;
    private int size;
    private int maxOccupancy;
    private int maxVehicles;
    private int petsAllowed;
    private Electric electric;
    private Water water;
    private Sewer sewer;

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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(final String areaId) {
        this.areaId = areaId;
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
