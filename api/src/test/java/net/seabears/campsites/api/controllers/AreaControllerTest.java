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

import java.util.UUID;

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
        final String url = "/api/areas/0f20c7ef-c2cc-4431-85c0-74977fa2de63/campsites";
        final UUID campgroundId = UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96");
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"084bfb46-21cb-4c8c-8a9a-3d0d67002d28\","
                        + "\"campgroundId\":\"" + campgroundId + "\","
                        + "\"name\":\"Site A\"},"
                        + "{\"id\":\"7603ff4e-8515-4e20-be6f-ae3a58669508\","
                        + "\"campgroundId\":\"" + campgroundId + "\","
                        + "\"name\":\"Site B\"}]"));
    }

    @Test
    public void getAreas() throws Exception {
        final UUID id = UUID.fromString("0f20c7ef-c2cc-4431-85c0-74977fa2de63");
        final UUID campgroundId = UUID.fromString("9cfa88ec-803d-4f22-83b5-af301af9ca96");
        mvc.perform(MockMvcRequestBuilders.get("/api/areas/{0}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"" + id + "\","
                        + "\"campgroundId\":\"" + campgroundId + "\","
                        + "\"name\":\"Campground X\","
                        + "\"description\":\"Entire campground\"}"));
    }
}