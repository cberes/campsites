package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.controllers.exceptions.BadArgumentException;
import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Campsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campsites")
public class CampsiteController {
    private final CampsiteDao dao;

    @Autowired
    public CampsiteController(final CampsiteDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public Campsite createCampsite(@RequestBody final Campsite campsite) {
        if (campsite.getId() != 0L) {
            throw new BadArgumentException("campsite", "id must be zero");
        }
        return dao.save(campsite);
    }

    @GetMapping("/{id}")
    public Campsite getCampsite(@PathVariable final long id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Campsite.class, id));
    }
}
