package br.com.erudio;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.models.Person;
import br.com.erudio.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonEndpointTests {
    
    final String BASE_PATH = "http://localhost:8888/person";

	@Autowired
	private PersonRepository repository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        repository.save(new Person("Diego", "Samuel", "Asa Sul"));
        repository.save(new Person("Eudes", "Silva", "Rosário"));
        repository.save(new Person("Anderson", "Silva", "Santa Teresa"));
		repository.save(new Person("Alice", "Ferreira", "Bombeiros"));
		repository.save(new Person("Alan", "Franco", "Granja Marileusa"));        
       
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testCreatePerson() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/products");

        Person person = new Person("Leandro", "Costa", "Presidente Roosevelt");
        Person response = restTemplate.postForObject(BASE_PATH, person, Person.class);
        Assert.assertEquals("Leandro Costa", response.getFirstName() + " " + response.getLastName());
    }
    
    @Test
    public void testFindOne() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Person> persons = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Person.class));
        Person person = restTemplate.getForObject(BASE_PATH + "/" + persons.get(1).getIdPerson(), Person.class);
        Assert.assertNotNull(person);
    	Assert.assertEquals("Eudes", person.getFirstName());
    	Assert.assertEquals("Silva", person.getLastName());
    }
    
    @Test
    public void testUpdatePerson() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Person> persons = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Person.class));
        
        Person person = restTemplate.getForObject(BASE_PATH + "/" + persons.get(2).getIdPerson(), Person.class);
        person.setFirstName("Tiago");
        person.setLastName("Ferreira");
        restTemplate.put(BASE_PATH, person);
        
        Person person2 = restTemplate.getForObject(BASE_PATH + "/" + persons.get(2).getIdPerson(), Person.class);
        Assert.assertNotNull(person2);
    	Assert.assertEquals("Tiago", person2.getFirstName());
    	Assert.assertEquals("Ferreira", person2.getLastName());
        
    }
    
    @Test
    public void testFindAll() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Person> persons = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Person.class));
    	Assert.assertEquals("Diego", persons.get(0).getFirstName());
    	Assert.assertEquals("Samuel", persons.get(0).getLastName());
    }

}