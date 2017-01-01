package br.com.erudio.web.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import br.com.erudio.models.Customer;
import br.com.erudio.services.CustomerService;

@Api(value = "customer")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Find customer by ID" )
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer get(@PathVariable(value = "customerId") String customerId){
        Customer customer = customerService.findById(customerId);
        addHATEOASSupport(customer, customerId);
		return customer;
    }
	
	@ApiOperation(value = "Find all customers" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAll(){
		List<Customer> customers = customerService.findAll();
		ArrayList<Customer> customersReturn = new ArrayList<Customer>();
		for (Customer customer : customers) {
			String idCustomer = customer.getIdCustomer();
			addHATEOASSupport(customer, idCustomer);
			customersReturn.add(customer);
		}
		return customersReturn;
	}
	
	@ApiOperation(value = "Create a new customer" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer create(@RequestBody Customer customer){
		Customer createdCustomer = customerService.create(customer);
		String idCustomer = createdCustomer.getIdCustomer();
		addHATEOASSupport(createdCustomer, idCustomer);
		return createdCustomer;
	}
	
	@ApiOperation(value = "Update an existing customer")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customer update(@RequestBody Customer customer){
		Customer updatedCustomer = customerService.update(customer);
		String idCustomer = updatedCustomer.getIdCustomer();
		addHATEOASSupport(updatedCustomer, idCustomer);		
		return updatedCustomer;
	}

	@ApiOperation(value = "Delete customer by ID" )
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "customerId") String customerId){
        customerService.delete(customerId);
    }

	private void addHATEOASSupport(Customer customer, String idCustomer) {
		if (customer != null && customer.getIdCustomer() != null) customer.add(linkTo(methodOn(CustomerController.class).get(idCustomer)).withSelfRel());
	}
}
