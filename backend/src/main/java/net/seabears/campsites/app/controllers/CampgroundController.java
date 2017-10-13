package net.seabears.campsites.app.controllers;

import java.util.List;

import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.domain.Campsite;
import net.seabears.campsites.app.dao.AreaDao;
import net.seabears.campsites.app.dao.CampsiteDao;
import net.seabears.campsites.app.domain.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seabears.campsites.app.dao.CampgroundDao;
import net.seabears.campsites.app.domain.Campground;

@RestController
@RequestMapping("/api/campground")
public class CampgroundController {
    private final AreaDao areaDao;
    private final CampgroundDao campgroundDao;
    private final CampsiteDao campsiteDao;

    @Autowired
    public CampgroundController(final AreaDao areaDao, final CampgroundDao campgroundDao, final CampsiteDao campsiteDao) {
        this.areaDao = areaDao;
        this.campgroundDao = campgroundDao;
        this.campsiteDao = campsiteDao;
    }

    @GetMapping("")
    public List<Campground> getCampgrounds() {
        return campgroundDao.findAll();
    }

    @GetMapping("/{id}")
    public Campground getCampgrounds(@PathVariable final String id) {
        return campgroundDao.find(id).orElseThrow(() -> new ResourceNotFoundException(Campground.class, id));
    }

    @GetMapping("/{id}/areas")
    public List<Area> getAreas(@PathVariable final String id) {
        return areaDao.findAllInCampground(id);
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final String id) {
        return campsiteDao.findAllInCampground(id);
    }
}
