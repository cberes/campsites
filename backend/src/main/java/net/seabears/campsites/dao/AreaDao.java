package net.seabears.campsites.dao;

import net.seabears.campsites.domain.Area;

import java.util.List;
import java.util.Optional;

public interface AreaDao {
    List<Area> findAllInCampground(final String id);

    Optional<Area> find(String id);
}
