package net.seabears.campsites.app.controllers;

import java.util.UUID;

import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.dao.CampsiteDao;
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
    public Campsite getCampsite(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return dao.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(Campsite.class, id));
    }
}
