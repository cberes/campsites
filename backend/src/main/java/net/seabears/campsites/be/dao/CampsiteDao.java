package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Campsite;
import org.springframework.data.repository.CrudRepository;

public interface CampsiteDao extends CrudRepository<Campsite, Long> {
    Iterable<Campsite> findByCampgroundId(Long id);

    Iterable<Campsite> findByAreaId(Long id);
}
