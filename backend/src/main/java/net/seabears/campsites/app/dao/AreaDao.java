package net.seabears.campsites.app.dao;

import net.seabears.campsites.app.domain.Area;

import java.util.List;
import java.util.Optional;

public interface AreaDao {
    List<Area> findAllInCampground(final String id);

    Optional<Area> find(String id);
}
