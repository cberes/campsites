package net.seabears.campsites.be.data;

import net.seabears.campsites.db.domain.Customer;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

public final class MockCustomerData {
    private MockCustomerData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Customer> load(final TestEntityManager em) {
        final Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Washington");
        customer.setEmail("gwashington@whitehouse.gov");
        customer.setPhone("2025551234");
        return List.of(em.persist(customer));
    }
}
