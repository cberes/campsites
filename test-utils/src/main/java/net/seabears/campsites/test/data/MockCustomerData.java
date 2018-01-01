package net.seabears.campsites.test.data;

import net.seabears.campsites.db.domain.Customer;

import java.util.List;
import java.util.function.UnaryOperator;

public final class MockCustomerData {
    private MockCustomerData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Customer> load(final UnaryOperator<Customer> persist) {
        final Customer customer = new Customer();
        customer.setFirstName("George");
        customer.setLastName("Washington");
        customer.setEmail("gwashington@whitehouse.gov");
        customer.setPhone("2025551234");
        return List.of(persist.apply(customer));
    }
}
