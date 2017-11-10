package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Customer;

class CustomerIdSerializer extends IdSerializer<Customer> {
    public CustomerIdSerializer() {
        super(Customer::getId);
    }
}
