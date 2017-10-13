package net.seabears.campsites.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.seabears.campsites.app.domain.Campground;
import org.springframework.data.repository.CrudRepository;

public interface CampgroundDao {
    List<Campground> findAll();

    Optional<Campground> find(String id);
}
