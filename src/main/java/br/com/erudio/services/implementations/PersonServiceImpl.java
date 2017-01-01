package br.com.erudio.services.implementations;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.models.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	private static final Logger LOGGER = Logger.getLogger(PersonServiceImpl.class);
    
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person create(Person person) {
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.info("Creating a person");
    	}
		return personRepository.save(person);
    }

    @Override
    public Person findById(String personId) {
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.info("Finding a person by ID");
    	}
        return personRepository.findByIdPerson(personId);
    }

    @Override
    public List<Person> findAll() {
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.info("Finding all persons");
    	}
    	return personRepository.findAll();
    }

    @Override
    public Person update(Person person) {
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.info("Updating a person");
    	}
    	return personRepository.save(person);
    }

    @Override
    public void delete(String personId) {
    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.info("Deleting a person");
    	}
    	personRepository.delete(personId);
    }
}
