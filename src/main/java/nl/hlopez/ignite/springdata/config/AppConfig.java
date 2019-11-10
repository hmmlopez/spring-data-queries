package nl.hlopez.ignite.springdata.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import nl.hlopez.ignite.springdata.model.Person;
import nl.hlopez.ignite.springdata.repository.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Configuration
@EnableIgniteRepositories(basePackageClasses = {PersonRepository.class}, queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class AppConfig {

    private static final String IGNITE_HOME = System.getProperty("user.dir") + "/target/ignite";
    private long size512MB = 512L * 1024 * 1024;

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration()
                .setIgniteHome(IGNITE_HOME)
                .setGridLogger(new Slf4jLogger())
                .setMetricsLogFrequency(0);

        CacheConfiguration cacheConfiguration = new CacheConfiguration<>("PersonCache")
                .setIndexedTypes(Long.class, Person.class);

        igniteConfiguration.setCacheConfiguration(cacheConfiguration);

        DataStorageConfiguration dataStorageConfiguration = new DataStorageConfiguration()
                .setDefaultDataRegionConfiguration(new DataRegionConfiguration()
                        .setInitialSize(size512MB)
                        .setMaxSize(size512MB)
                );

        igniteConfiguration.setDataStorageConfiguration(dataStorageConfiguration);

        return Ignition.start(igniteConfiguration);
    }

}
