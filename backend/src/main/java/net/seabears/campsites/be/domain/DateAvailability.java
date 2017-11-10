package net.seabears.campsites.be.domain;

import net.seabears.campsites.enums.Availability;

import java.time.LocalDate;

public class DateAvailability {
    private LocalDate date;
    private Availability status;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public Availability getStatus() {
        return status;
    }

    public void setStatus(final Availability status) {
        this.status = status;
    }
}
