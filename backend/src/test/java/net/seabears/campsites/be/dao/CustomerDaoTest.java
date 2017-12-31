package net.seabears.campsites.be.dao;

import net.seabears.campsites.be.config.TestConfiguration;
import net.seabears.campsites.be.data.MockCustomerData;
import net.seabears.campsites.db.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfiguration.class)
@DataJpaTest
public class CustomerDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerDao dao;

    @Test
    public void findByIdShouldReturnCustomer() {
        final long id = MockCustomerData.load(entityManager).get(0).getId();
        Optional<Customer> customer = dao.findById(1L);

        assertThat(customer.isPresent(), is(true));
        assertThat(customer.get().getFirstName(), is("George"));
    }
}
