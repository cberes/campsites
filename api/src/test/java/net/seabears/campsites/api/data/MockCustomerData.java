package net.seabears.campsites.api.data;

import net.seabears.campsites.db.domain.Customer;

import java.util.List;
import java.util.Optional;

public final class MockCustomerData {
    private MockCustomerData() {
        throw new UnsupportedOperationException("cannot instantiate " + getClass());
    }

    public static List<Customer> allData() {
        final Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("George");
        customer.setLastName("Washington");
        customer.setEmail("gwashington@whitehouse.gov");
        customer.setPhone("2025551234");
        return List.of(customer);
    }

    public static Optional<Customer> get(final int i) {
        final List<Customer> data = allData();
        return i < 0 || i >= data.size() ? Optional.empty() : Optional.of(data.get(i));
    }
}
