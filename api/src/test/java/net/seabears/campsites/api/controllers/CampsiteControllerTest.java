package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.db.MockDatabaseConfig;
import org.junit.jupiter.api.BeforeEach;
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
@SpringBootTest //(classes = MockDatabaseConfig.class)
@AutoConfigureMockMvc
@DataJpaTest
public class CampsiteControllerTest {
    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private MockDatabaseConfig mockData;
//
//    @BeforeEach
//    public void setup() {
//        mockData.load();
//    }

    @Test
    public void getCampsites() throws Exception {
        final UUID id = UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508");
        mvc.perform(MockMvcRequestBuilders.get("/api/campsites/{0}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"" + id + "\","
                        + "\"campgroundId\":\"9cfa88ec-803d-4f22-83b5-af301af9ca96\","
                        + "\"name\":\"Site B\"}"));
    }
}