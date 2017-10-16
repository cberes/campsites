package net.seabears.campsites.app.dao.mock;

import net.seabears.campsites.db.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Repository
public class MockCustomerDao extends InMemoryCrudRepository<Customer, UUID> {
    @Override
    protected BiConsumer<Customer, UUID> idSetter() {
        return Customer::setId;
    }

    @Override
    protected Function<Customer, UUID> idGetter() {
        return Customer::getId;
    }

    @Override
    protected UUID newId() {
        return UUID.randomUUID();
    }
}
