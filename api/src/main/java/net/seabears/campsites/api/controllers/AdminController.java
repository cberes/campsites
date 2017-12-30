package net.seabears.campsites.api.controllers;

import net.seabears.campsites.be.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final DatabaseService dbService;

    @Autowired
    public AdminController(final DatabaseService dbService) {
        this.dbService = dbService;
    }

    @PostMapping("/database")
    public void createDatabase() {
        dbService.update(false);
    }

    @PatchMapping("/database")
    public void updateDatabase() {
        dbService.update(true);
    }
}
