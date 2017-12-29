package net.seabears.campsites.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DataJpaTest
public class CampgroundControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCampgrounds() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,"
                        + "\"name\":\"Campground X\"},"
                        + "{\"id\":2,"
                        + "\"name\":\"Campground Y\"}]"));
    }

    @Test
    public void getCampground() throws Exception {
        final long id = 2L;
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + id + ",\"name\":\"Campground Y\"}"));
    }

    @Test
    public void getAreas() throws Exception {
        final long id = 1L;
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/{id}/areas", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,"
                        + "\"campgroundId\":" + id + ","
                        + "\"name\":\"Campground X\","
                        + "\"description\":\"Entire campground\"}]"));
    }

    @Test
    public void getCampsites() throws Exception {
        final long id = 1L;
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/{id}/campsites", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,"
                        + "\"campgroundId\":" + id + ","
                        + "\"name\":\"Site A\"},"
                        + "{\"id\":2,"
                        + "\"campgroundId\":" + id + ","
                        + "\"name\":\"Site B\"}]"));
    }
}