package br.com.erudio.services;

import java.util.List;

import br.com.erudio.models.Customer;

public interface CustomerService {
    
    Customer create(final Customer customer);
    Customer findById(final String customerId);
    List<Customer> findAll();
    Customer update(Customer customer);
    void delete(final String customerId);

}
