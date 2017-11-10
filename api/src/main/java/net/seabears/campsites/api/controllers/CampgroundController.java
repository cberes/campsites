package net.seabears.campsites.api.controllers;

import java.util.List;
import java.util.UUID;

import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampgroundDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.seabears.campsites.api.controllers.util.ControllerUtils.toDtoList;

@RestController
@RequestMapping("/campgrounds")
public class CampgroundController {
    private final AreaDao areaDao;
    private final CampgroundDao campgroundDao;
    private final CampsiteDao campsiteDao;

    @Autowired
    public CampgroundController(final AreaDao areaDao,
                                final CampgroundDao campgroundDao,
                                final CampsiteDao campsiteDao) {
        this.areaDao = areaDao;
        this.campgroundDao = campgroundDao;
        this.campsiteDao = campsiteDao;
    }

    @GetMapping("")
    public List<Campground> getCampgrounds() {
        return toDtoList(campgroundDao.findAll());
    }

    @GetMapping("/{id}")
    public Campground getCampgrounds(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return campgroundDao.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(Campground.class, id));
    }

    @GetMapping("/{id}/areas")
    public List<Area> getAreas(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return toDtoList(areaDao.findByCampgroundId(uuid));
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return toDtoList(campsiteDao.findByCampgroundId(uuid));
    }
}
