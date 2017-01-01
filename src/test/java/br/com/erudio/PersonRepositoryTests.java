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

import br.com.erudio.models.Person;
import br.com.erudio.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonRepositoryTests {

    @Autowired
    PersonRepository repository;
    
    Person diego, tiago, leandro;

    @Before
    public void setUp() {

        repository.deleteAll();

        diego = repository.save(new Person("Diego", "Samuel", "Asa Sul"));
        tiago = repository.save(new Person("Tiago Daniel", "Samuel", "Santa Tereza"));
        leandro = repository.save(new Person("Leandro", "Costa", "Presidente Roosevelt"));
    }

    @Test
    public void setsIdOnSave() {
        Person diego = repository.save(new Person("Diego", "Samuel", "Asa Sul"));
        assertThat(diego.getIdPerson()).isNotNull();
    }

    @Test
    public void findsByLastName() {
        List<Person> result = repository.findByLastName("Costa");
        assertThat(result).hasSize(1).extracting("firstName").contains("Leandro");
    }
    
    @Test
    public void findByAddress() {
    	List<Person> result = repository.findByAddress("Presidente Roosevelt");
    	assertThat(result).hasSize(1).extracting("firstName").contains("Leandro");
    }

    @Test
    public void findsByExample() {
        Person probe = new Person(null, "Samuel", null);
        List<Person> result = repository.findAll(Example.of(probe));
        assertThat(result).hasSize(2).extracting("firstName").contains("Diego", "Tiago Daniel");
    }
}
