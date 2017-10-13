package net.seabears.campsites.app.dao;

import net.seabears.campsites.app.domain.Area;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AreaDao extends CrudRepository<Area, UUID> {
    Iterable<Area> findByCampgroundId(final String id);
}
