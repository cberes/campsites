package net.seabears.campsites.app.dao;

import net.seabears.campsites.app.domain.Campsite;

import java.util.List;
import java.util.Optional;

public interface CampsiteDao {
    List<Campsite> findAllInCampground(String id);

    List<Campsite> findAllInArea(String id);

    Optional<Campsite> find(String id);
}
