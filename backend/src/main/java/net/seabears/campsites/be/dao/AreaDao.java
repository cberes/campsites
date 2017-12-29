package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Area;
import org.springframework.data.repository.CrudRepository;

public interface AreaDao extends CrudRepository<Area, Long> {
    Iterable<Area> findByCampgroundId(final Long id);
}
