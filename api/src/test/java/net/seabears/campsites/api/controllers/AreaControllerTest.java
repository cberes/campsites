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
public class AreaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCampsites() throws Exception {
        final String url = "/api/areas/{id}/campsites";
        final long campgroundId = 1L;
        mvc.perform(MockMvcRequestBuilders.get(url, campgroundId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,"
                        + "\"campgroundId\":" + campgroundId + ","
                        + "\"name\":\"Site A\"},"
                        + "{\"id\":2,"
                        + "\"campgroundId\":" + campgroundId + ","
                        + "\"name\":\"Site B\"}]"));
    }

    @Test
    public void getAreas() throws Exception {
        final long id = 1L;
        final long campgroundId = 1L;
        mvc.perform(MockMvcRequestBuilders.get("/api/areas/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + id + ","
                        + "\"campgroundId\":" + campgroundId + ","
                        + "\"name\":\"Campground X\","
                        + "\"description\":\"Entire campground\"}"));
    }
}