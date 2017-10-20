package net.seabears.campsites.app.controllers;

import net.seabears.campsites.app.adapters.CustomerDtoAdapter;
import net.seabears.campsites.app.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.app.dao.CustomerDao;
import net.seabears.campsites.app.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerDao dao;
    private final CustomerDtoAdapter dtoAdapter;

    @Autowired
    public CustomerController(final CustomerDao dao, final CustomerDtoAdapter dtoAdapter) {
        this.dao = dao;
        this.dtoAdapter = dtoAdapter;
    }

    @GetMapping("/{id}")
    public Customer getArea(@PathVariable final String id) {
        final UUID uuid = UUID.fromString(id);
        return dao.findById(uuid)
                .map(dtoAdapter::adapt)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, id));
    }
}
