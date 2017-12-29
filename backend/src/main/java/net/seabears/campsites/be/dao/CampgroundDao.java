package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Campground;
import org.springframework.data.repository.CrudRepository;

public interface CampgroundDao extends CrudRepository<Campground, Long> {
}
