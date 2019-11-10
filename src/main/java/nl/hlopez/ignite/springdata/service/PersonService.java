package nl.hlopez.ignite.springdata.service;

import nl.hlopez.ignite.springdata.repository.PersonRepository;
import nl.hlopez.ignite.springdata.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person save(long key, Person person) {
        return this.repository.save(key, person);
    }

    public void deleteById(long key) {
        this.repository.deleteById(key);
    }

    public List<Person> findByLastname(String lastname) {
        return repository.findByLastname(lastname);
    }

    public List<Person> findByLastnameIgnoreCase(String lastname) {
        return repository.findByLastnameIgnoreCase(lastname.toUpperCase());
    }

    public List<Person> findByLastnameStartsWithIgnoreCase(String lastname) {
        return repository.findByLastnameStartsWithIgnoreCase(lastname.toUpperCase());
    }

    public List<Person> findByLastnameContainsIgnoreCase(String lastname) {
        return repository.findByLastnameContainsIgnoreCase(lastname.toUpperCase());
    }

}
