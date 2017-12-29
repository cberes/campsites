package net.seabears.campsites.be.dao;

import net.seabears.campsites.db.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Long> {
}
