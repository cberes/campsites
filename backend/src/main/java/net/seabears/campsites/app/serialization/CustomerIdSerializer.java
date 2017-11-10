package net.seabears.campsites.app.serialization;

import net.seabears.campsites.db.domain.Customer;

class CustomerIdSerializer extends IdSerializer<Customer> {
    public CustomerIdSerializer() {
        super(Customer::getId);
    }
}
