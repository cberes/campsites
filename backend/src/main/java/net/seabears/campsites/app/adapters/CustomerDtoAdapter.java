package net.seabears.campsites.app.adapters;

import net.seabears.campsites.app.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoAdapter implements Adapter<net.seabears.campsites.db.domain.Customer, Customer> {
    private static class View extends Customer {
        private final net.seabears.campsites.db.domain.Customer delegate;

        View(final net.seabears.campsites.db.domain.Customer delegate) {
            this.delegate = delegate;
        }
    }

    @Override
    public Customer adapt(final net.seabears.campsites.db.domain.Customer source) {
        return new View(source);
    }
}
