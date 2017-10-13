package net.seabears.campsites.app.controllers;

import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.domain.Campsite;
import net.seabears.campsites.app.dao.CampsiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campsite")
public class CampsiteController {
    private final CampsiteDao dao;

    @Autowired
    public CampsiteController(final CampsiteDao dao) {
        this.dao = dao;
    }

    @GetMapping("/{id}")
    public Campsite getCampsite(@PathVariable final String id) {
        return dao.find(id).orElseThrow(() -> new ResourceNotFoundException(Campsite.class, id));
    }
}
