package net.seabears.campsites.app.controllers;

import java.util.List;
import java.util.UUID;

import net.seabears.campsites.app.adapters.AreaDtoAdapter;
import net.seabears.campsites.app.adapters.CampgroundDtoAdapter;
import net.seabears.campsites.app.adapters.CampsiteDtoAdapter;
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

import static net.seabears.campsites.app.controllers.util.ControllerUtils.toDtoList;

@RestController
@RequestMapping("/api/campground")
public class CampgroundController {
    private final AreaDao areaDao;
    private final CampgroundDao campgroundDao;
    private final CampsiteDao campsiteDao;
    private final AreaDtoAdapter areaDtoAdapter;
    private final CampgroundDtoAdapter campgroundDtoAdapter;
    private final CampsiteDtoAdapter campsiteDtoAdapter;

    @Autowired
    public CampgroundController(final AreaDao areaDao, final CampgroundDao campgroundDao, final CampsiteDao campsiteDao,
                                final AreaDtoAdapter areaDtoAdapter, final CampgroundDtoAdapter campgroundDtoAdapter,
                                final CampsiteDtoAdapter campsiteDtoAdapter) {
        this.areaDao = areaDao;
        this.campgroundDao = campgroundDao;
        this.campsiteDao = campsiteDao;
        this.areaDtoAdapter = areaDtoAdapter;
        this.campgroundDtoAdapter = campgroundDtoAdapter;
        this.campsiteDtoAdapter = campsiteDtoAdapter;
    }

    @GetMapping("")
    public List<Campground> getCampgrounds() {
        return toDtoList(campgroundDao.findAll(), campgroundDtoAdapter);
    }

    @GetMapping("/{id}")
    public Campground getCampgrounds(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return campgroundDao.findById(uuid)
                .map(campgroundDtoAdapter::adapt)
                .orElseThrow(() -> new ResourceNotFoundException(Campground.class, id));
    }

    @GetMapping("/{id}/areas")
    public List<Area> getAreas(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return toDtoList(areaDao.findByCampgroundId(uuid), areaDtoAdapter);
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return toDtoList(campsiteDao.findByCampgroundId(uuid), campsiteDtoAdapter);
    }
}
