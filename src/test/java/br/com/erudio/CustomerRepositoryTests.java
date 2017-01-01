package br.com.erudio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.erudio.models.Customer;
import br.com.erudio.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository repository;
    
    Customer diego, tiago, leandro;

    @Before
    public void setUp() {

        repository.deleteAll();

        diego = repository.save(new Customer("Diego", "Samuel"));
        tiago = repository.save(new Customer("Tiago Daniel", "Samuel"));
        leandro = repository.save(new Customer("Leandro", "Costa"));
    }

    @Test
    public void setsIdOnSave() {
        Customer diego = repository.save(new Customer("Diego", "Samuel"));
        assertThat(diego.getIdCustomer()).isNotNull();
    }

    @Test
    public void findsByLastName() {
        List<Customer> result = repository.findByLastName("Costa");
        assertThat(result).hasSize(1).extracting("firstName").contains("Leandro");
    }

    @Test
    public void findsByExample() {
        Customer probe = new Customer(null, "Samuel");
        List<Customer> result = repository.findAll(Example.of(probe));
        assertThat(result).hasSize(2).extracting("firstName").contains("Diego", "Tiago Daniel");
    }
}
