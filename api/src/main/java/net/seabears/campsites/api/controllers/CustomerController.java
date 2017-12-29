package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.be.dao.CustomerDao;
import net.seabears.campsites.db.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerDao dao;

    @Autowired
    public CustomerController(final CustomerDao dao) {
        this.dao = dao;
    }

    @GetMapping("/{id}")
    public Customer getArea(@PathVariable final long id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, id));
    }
}
