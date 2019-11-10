package nl.hlopez.ignite.springdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

@Data
@AllArgsConstructor
public class Person {

    private String firstname;
    @QuerySqlField
    private String lastname;
    private int age;

}
