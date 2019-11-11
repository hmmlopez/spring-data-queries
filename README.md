# Apache Ignite with Spring Data (Queries)
Demo project for querying in Apache Ignite with spring-data

This project is using the latest version of Apache Ignite and Spring Boot at this moment in it's dependencies:

| Application | Version |
|:------:|:--------|
|Apache Ignite| 2.7.6|
|Spring Boot | 2.2.0.RELEASE |
| \* spring-data-commons | 2.0.14.RELEASE |

\* Because of some changes in the Api of spring-data-commons introduced in 2.1.x.RELEASE, we have to use an older version. Ignite ignite-spring-data_2.0 will work with the newer version as long you don't specify any @Query("") statment in the repository interface. But in this case we would like to use that feature, so the work around is to specify the older version. Hope this is fixed in the next release.

## SQL Query
In this demo, you can see how you can use your query using **IgnoreCase** functionality, which is comes out of spring-data but not supported by Apache Ignite. In the code this is shown in the [Repository](https://github.com/hmmlopez/spring-data-queries/blob/master/src/main/java/nl/hlopez/ignite/springdata/repository/PersonRepository.java) in combination with the [Service](https://github.com/hmmlopez/spring-data-queries/blob/master/src/main/java/nl/hlopez/ignite/springdata/service/PersonService.java) how to accomplish this.

```java
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
```
