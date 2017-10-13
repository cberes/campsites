package net.seabears.campsites.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("net.seabears.campsites.db")
@SpringBootApplication
public class CampsitesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampsitesApplication.class, args);
    }
}
