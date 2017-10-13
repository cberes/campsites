package net.seabears.campsites.app.dao;

import java.util.UUID;

import net.seabears.campsites.app.domain.Campground;
import org.springframework.data.repository.CrudRepository;

public interface CampgroundDao extends CrudRepository<Campground, UUID> {
}
