package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDatabaseAdapter implements Adapter<Customer, net.seabears.campsites.db.domain.Customer> {
    private static class View extends net.seabears.campsites.db.domain.Customer {
        private final Customer customer;

        View(final Customer customer) {
            this.customer = customer;
        }
    }

    @Override
    public net.seabears.campsites.db.domain.Customer adapt(final Customer source) {
        return new View(source);
    }
}
