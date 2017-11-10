package net.seabears.campsites.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CampgroundControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCampgrounds() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"name\":\"Campground X\"},"
                        + "{\"id\":\"2\",\"name\":\"Campground Y\"}]"));
    }

    @Test
    public void getCampground() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"2\",\"name\":\"Campground Y\"}"));
    }

    @Test
    public void getAreas() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/1/areas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"campgroundId\":\"1\",\"name\":\"Campground X\","
                        + "\"description\":\"Entire campground\"}]"));
    }

    @Test
    public void getCampsites() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/1/campsites").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"campgroundId\":\"1\",\"name\":\"Site A\"},"
                        + "{\"id\":\"2\",\"campgroundId\":\"1\",\"name\":\"Site B\"}]"));
    }
}