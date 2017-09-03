package net.seabears.campsites.dao;

import java.util.List;
import java.util.Optional;

import net.seabears.campsites.domain.Campground;

public interface CampgroundDao {
    List<Campground> findAll();

    Optional<Campground> find(String id);
}
