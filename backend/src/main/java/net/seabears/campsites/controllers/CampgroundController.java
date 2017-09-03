package net.seabears.campsites.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seabears.campsites.dao.CampgroundDao;
import net.seabears.campsites.domain.Campground;

@RestController
@RequestMapping("/api/campground")
public class CampgroundController {
    private final CampgroundDao dao;

    public CampgroundController(final CampgroundDao dao) {
        this.dao = dao;
    }

    @GetMapping("")
    public List<Campground> getCampgrounds() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Campground getCampgrounds(@PathVariable final String id) {
        return dao.find(id).orElse(null);
    }
}
