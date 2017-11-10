package net.seabears.campsites.app.controllers;

import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.dao.AreaDao;
import net.seabears.campsites.app.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static net.seabears.campsites.app.controllers.util.ControllerUtils.toDtoList;

@RestController
@RequestMapping("/areas")
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
        final UUID uuid = UUID.fromString(id);
        return areaDao.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(Area.class, id));
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return toDtoList(campsiteDao.findByAreaId(uuid));
    }
}
