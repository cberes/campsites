package net.seabears.campsites.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = {
        net.seabears.campsites.be.Components.class,
        net.seabears.campsites.api.controllers.Controllers.class,
        net.seabears.campsites.api.serialization.Serialization.class
})
@EnableJpaRepositories(basePackageClasses = net.seabears.campsites.be.dao.Repositories.class)
@EntityScan(basePackageClasses = net.seabears.campsites.db.Entities.class)
@SpringBootApplication
public class CampsitesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampsitesApplication.class, args);
    }
}
