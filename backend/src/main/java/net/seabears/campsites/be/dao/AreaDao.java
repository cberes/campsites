package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Area;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AreaDao extends CrudRepository<Area, UUID> {
    Iterable<Area> findByCampgroundId(final UUID id);
}
