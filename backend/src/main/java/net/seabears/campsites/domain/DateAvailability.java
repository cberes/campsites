package net.seabears.campsites.domain;

import net.seabears.campsites.domain.enums.Availability;

import java.time.LocalDate;

public class DateAvailability {
    private LocalDate date;
    private Availability availability;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(final Availability availability) {
        this.availability = availability;
    }
}
