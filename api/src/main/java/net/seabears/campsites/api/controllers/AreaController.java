package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.controllers.exceptions.BadArgumentException;
import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Area;
import net.seabears.campsites.db.domain.Campsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.seabears.campsites.api.controllers.util.ControllerUtils.toDtoList;

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

    @PostMapping
    public Area createArea(@RequestBody final Area area) {
        if (area.getId() != 0L) {
            throw new BadArgumentException("area", "id must be zero");
        }
        return areaDao.save(area);
    }

    @GetMapping("/{id}")
    public Area getArea(@PathVariable final long id) {
        return areaDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Area.class, id));
    }

    @GetMapping("/{id}/campsites")
    public List<Campsite> getCampsites(@PathVariable final long id) {
        return toDtoList(campsiteDao.findByAreaId(id));
    }
}
