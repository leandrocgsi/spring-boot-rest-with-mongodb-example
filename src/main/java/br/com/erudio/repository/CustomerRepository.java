package br.com.erudio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.erudio.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public Customer findByIdCustomer(String id);
    public List<Customer> findByLastName(String lastName);

}