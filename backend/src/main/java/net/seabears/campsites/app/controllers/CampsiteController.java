package net.seabears.campsites.app.controllers;

import net.seabears.campsites.app.adapters.CampsiteDtoAdapter;
import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.domain.Campsite;
import net.seabears.campsites.app.dao.CampsiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/campsite")
public class CampsiteController {
    private final CampsiteDao dao;
    private final CampsiteDtoAdapter dtoAdapter;

    @Autowired
    public CampsiteController(final CampsiteDao dao, final CampsiteDtoAdapter dtoAdapter) {
        this.dao = dao;
        this.dtoAdapter = dtoAdapter;
    }

    @GetMapping("/{id}")
    public Campsite getCampsite(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return dao.findById(uuid)
                .map(dtoAdapter::adapt)
                .orElseThrow(() -> new ResourceNotFoundException(Campsite.class, id));
    }
}
