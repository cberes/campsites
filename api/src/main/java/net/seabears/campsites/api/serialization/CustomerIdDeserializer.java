package net.seabears.campsites.api.serialization;

import net.seabears.campsites.db.domain.Customer;

class CustomerIdDeserializer extends IdDeserializer<Customer> {
    public CustomerIdDeserializer() {
        super(Customer::setId, Customer.class);
    }
}
