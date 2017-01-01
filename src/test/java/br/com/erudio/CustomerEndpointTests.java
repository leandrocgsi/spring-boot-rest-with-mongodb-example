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

import br.com.erudio.models.Customer;
import br.com.erudio.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CustomerEndpointTests {
    
    final String BASE_PATH = "http://localhost:8888/customer";

	@Autowired
	private CustomerRepository repository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        repository.save(new Customer("Diego", "Samuel"));
        repository.save(new Customer("Eudes", "Silva"));
        repository.save(new Customer("Anderson", "Silva"));
		repository.save(new Customer("Alice", "Ferreira"));
		repository.save(new Customer("Alan", "Franco"));        
       
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testCreateCustomer() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/products");

        Customer customer = new Customer("Leandro", "Costa");
        Customer response = restTemplate.postForObject(BASE_PATH, customer, Customer.class);
        Assert.assertEquals("Leandro Costa", response.getFirstName() + " " + response.getLastName());
    }
    
    @Test
    public void testFindOne() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Customer> customers = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Customer.class));
        Customer customer = restTemplate.getForObject(BASE_PATH + "/" + customers.get(1).getIdCustomer(), Customer.class);
        Assert.assertNotNull(customer);
    	Assert.assertEquals("Eudes", customer.getFirstName());
    	Assert.assertEquals("Silva", customer.getLastName());
    }
    
    @Test
    public void testUpdateCustomer() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Customer> customers = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Customer.class));
        
        Customer customer = restTemplate.getForObject(BASE_PATH + "/" + customers.get(2).getIdCustomer(), Customer.class);
        customer.setFirstName("Tiago");
        customer.setLastName("Ferreira");
        restTemplate.put(BASE_PATH, customer);
        
        Customer customer2 = restTemplate.getForObject(BASE_PATH + "/" + customers.get(2).getIdCustomer(), Customer.class);
        Assert.assertNotNull(customer2);
    	Assert.assertEquals("Tiago", customer2.getFirstName());
    	Assert.assertEquals("Ferreira", customer2.getLastName());
        
    }
    
    @Test
    public void testFindAll() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Customer> customers = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Customer.class));
    	Assert.assertEquals("Diego", customers.get(0).getFirstName());
    	Assert.assertEquals("Samuel", customers.get(0).getLastName());
    }

}

//https://dzone.com/articles/unit-and-integration-tests-in-spring-boot