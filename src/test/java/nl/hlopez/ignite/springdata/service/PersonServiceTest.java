package nl.hlopez.ignite.springdata.service;

import nl.hlopez.ignite.springdata.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private final long key = 1;
    private final Person person = new Person("Albert", "Einstein", 56);
    private final List<Person> expected = Arrays.asList(person);

    @BeforeEach
    void beforeEach() {
        personService.save(key, person);
    }

    @AfterEach
    void afterEach() {
        personService.deleteById(key);
    }

    @Test
    void findByLastname() {
        assertThat(expected).isEqualTo(personService.findByLastname("Einstein"));
    }

    @Test
    void findByLastnameIgnoreCase() {
        assertThat(expected).isEqualTo(personService.findByLastnameIgnoreCase("einstein"));
    }

    @Test
    void findByLastnameStartsWithIgnoreCase() {
        assertThat(expected).isEqualTo(personService.findByLastnameStartsWithIgnoreCase("EIN"));
    }

    @Test
    void findByLastnameContainsIgnoreCase() {
        assertThat(expected).isEqualTo(personService.findByLastnameContainsIgnoreCase("iNSt"));
    }

}