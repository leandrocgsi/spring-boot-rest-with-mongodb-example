package br.com.erudio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.erudio.models.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByFirstName(String firstName);
    public Person findByIdPerson(String id);
    public List<Person> findByAddress(String address);
    public List<Person> findByLastName(String lastName);

}