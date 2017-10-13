package net.seabears.campsites.app.controllers;

import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.dao.CampsiteDao;
import net.seabears.campsites.app.domain.Area;
import net.seabears.campsites.app.domain.Campsite;
import net.seabears.campsites.app.dao.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/area")
public class AreaController {
    private final AreaDao areaDao;
    private final CampsiteDao campsiteDao;

    @Autowired
    public AreaController(final AreaDao areaDao, final CampsiteDao campsiteDao) {
        this.areaDao = areaDao;
        this.campsiteDao = campsiteDao;
    }

    @GetMapping("/{id}")
    public Area getArea(@PathVariable final String id) {
        return areaDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(Area.class, id));
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final String id) {
        return campsiteDao.findByAreaId(id);
    }
}
