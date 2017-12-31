package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.controllers.exceptions.BadArgumentException;
import net.seabears.campsites.api.controllers.exceptions.ResourceNotFoundException;
import net.seabears.campsites.be.dao.CustomerDao;
import net.seabears.campsites.db.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerDao dao;

    @Autowired
    public CustomerController(final CustomerDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody final Customer customer) {
        if (customer.getId() != 0L) {
            throw new BadArgumentException("customer", "id must be zero");
        }
        return dao.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable final long id) {
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, id));
    }
}
