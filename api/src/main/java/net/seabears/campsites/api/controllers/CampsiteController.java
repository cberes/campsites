package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.be.dao.CampsiteDao;
import net.seabears.campsites.db.domain.Campsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campsites")
public class CampsiteController {
    private final CampsiteDao dao;

    @Autowired
    public CampsiteController(final CampsiteDao dao) {
        this.dao = dao;
    }

    @GetMapping("/{id}")
    public Campsite getCampsite(@PathVariable final long id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Campsite.class, id));
    }
}
