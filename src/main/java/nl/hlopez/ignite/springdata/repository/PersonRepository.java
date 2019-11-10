package nl.hlopez.ignite.springdata.repository;

import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.Query;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;
import nl.hlopez.ignite.springdata.model.Person;

import java.util.List;

@RepositoryConfig(cacheName = "PersonCache")
public interface PersonRepository extends IgniteRepository<Person, Long> {

    List<Person> findByLastname(String lastname);

    @Query("SELECT * FROM person WHERE UPPER(lastname) LIKE ?")
    List<Person> findByLastnameIgnoreCase(String lastname);

    @Query("SELECT * FROM person WHERE UPPER(lastname) LIKE '%' || ?")
    List<Person> findByLastnameStartsWithIgnoreCase(String lastname);

    @Query("SELECT * FROM person WHERE UPPER(lastname) LIKE '%' || ? || '%'")
    List<Person> findByLastnameContainsIgnoreCase(String lastname);

}
