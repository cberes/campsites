package net.seabears.campsites.be.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackageClasses = net.seabears.campsites.be.dao.Repositories.class)
@EntityScan(basePackageClasses = net.seabears.campsites.db.Entities.class)
public class TestConfiguration {
}
