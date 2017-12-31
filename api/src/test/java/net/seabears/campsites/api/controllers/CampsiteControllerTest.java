package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.data.MockCampsiteData;
import net.seabears.campsites.be.dao.CampsiteDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CampsiteController.class)
public class CampsiteControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CampsiteDao dao;

    @Test
    public void getCampsites() throws Exception {
        final long id = 2L;
        given(dao.findById(id)).willReturn(MockCampsiteData.get(1));

        mvc.perform(MockMvcRequestBuilders.get("/campsites/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + id + ",\"name\":\"Site B\"}"));
    }
}