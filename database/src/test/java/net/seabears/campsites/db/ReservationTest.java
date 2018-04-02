package net.seabears.campsites.db;

import net.seabears.campsites.db.domain.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {
    @Test
    public void testNights() {
        final Reservation reservation = new Reservation();
        reservation.setStarting(LocalDate.of(2018, 4, 1));
        reservation.setEnding(LocalDate.of(2018, 4, 3));
        assertEquals(reservation.getNights(), 2);
    }
}
