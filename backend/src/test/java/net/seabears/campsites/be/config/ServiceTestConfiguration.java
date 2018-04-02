package net.seabears.campsites.be.config;

import net.seabears.campsites.be.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan("net.seabears.campsites.be.service.impl")
public class ServiceTestConfiguration {
    @Bean
    public AreaDao mockArea() {
        return mock(AreaDao.class);
    }

    @Bean
    public CampgroundDao mockCampground() {
        return mock(CampgroundDao.class);
    }

    @Bean
    public CampsiteDao mockCampsite() {
        return mock(CampsiteDao.class);
    }

    @Bean
    public CustomerDao mockCustomer() {
        return mock(CustomerDao.class);
    }

    @Bean
    public ReservationDao mockReservation() {
        return mock(ReservationDao.class);
    }
}
