package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Campsite;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CampsiteDao extends CrudRepository<Campsite, UUID> {
    Iterable<Campsite> findByCampgroundId(UUID id);

    Iterable<Campsite> findByAreaId(UUID id);
}
