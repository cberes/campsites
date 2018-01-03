package net.seabears.campsites.api.controllers;

import java.util.List;

import net.seabears.campsites.api.controllers.exceptions.BadArgumentException;
import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.api.controllers.util.ControllerUtils;
import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampgroundDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campground;
import net.seabears.campsites.db.domain.Campsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Campground createCampground(@RequestBody final Campground campground) {
        if (campground.getId() != 0L) {
            throw new BadArgumentException("campground", "id must be zero");
        }
        return campgroundDao.save(campground);
    }

    @GetMapping
    public List<Campground> getCampgrounds() {
        return ControllerUtils.toList(campgroundDao.findAll());
    }

    @GetMapping("/{id}")
    public Campground getCampgrounds(@PathVariable final long id) {
        return campgroundDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Campground.class, id));
    }

    @GetMapping("/{id}/areas")
    public List<Area> getAreas(@PathVariable final long id) {
        return ControllerUtils.toList(areaDao.findByCampgroundId(id));
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final long id) {
        return ControllerUtils.toList(campsiteDao.findByCampgroundId(id));
    }
}
