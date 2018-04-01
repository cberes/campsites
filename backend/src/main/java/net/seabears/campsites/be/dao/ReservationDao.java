package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationDao extends CrudRepository<Reservation, Long> {
}
