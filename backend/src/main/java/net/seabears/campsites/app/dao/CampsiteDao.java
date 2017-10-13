package net.seabears.campsites.app.dao;

import net.seabears.campsites.app.domain.Campsite;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CampsiteDao extends CrudRepository<Campsite, UUID> {
    Iterable<Campsite> findByCampgroundId(String id);

    Iterable<Campsite> findByAreaId(String id);
}
