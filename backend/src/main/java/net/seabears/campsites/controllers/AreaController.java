package net.seabears.campsites.controllers;

import net.seabears.campsites.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.dao.AreaDao;
import net.seabears.campsites.dao.CampsiteDao;
import net.seabears.campsites.domain.Area;
import net.seabears.campsites.domain.Campsite;
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
        return areaDao.find(id).orElseThrow(() -> new ResourceNotFoundException(Area.class, id));
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final String id) {
        return campsiteDao.findAllInArea(id);
    }
}
