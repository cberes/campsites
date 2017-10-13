package net.seabears.campsites.app.dao;

import net.seabears.campsites.db.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerDao extends CrudRepository<Customer, UUID> {
}
