package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.data.MockAreaData;
import net.seabears.campsites.api.data.MockCampsiteData;
import net.seabears.campsites.be.dao.AreaDao;
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
@WebMvcTest(AreaController.class)
public class AreaControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AreaDao areaDao;

    @MockBean
    private CampsiteDao campsiteDao;

    @Test
    public void getCampsites() throws Exception {
        final String url = "/areas/{id}/campsites";
        final long campgroundId = 1L;
        given(campsiteDao.findByAreaId(campgroundId)).willReturn(MockCampsiteData.allData());

        mvc.perform(MockMvcRequestBuilders.get(url, campgroundId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Site A\"},"
                        + "{\"id\":2,\"name\":\"Site B\"}]"));
    }

    @Test
    public void getAreas() throws Exception {
        final long id = 1L;
        final long campgroundId = 1L;
        given(areaDao.findById(campgroundId)).willReturn(MockAreaData.get(0));

        mvc.perform(MockMvcRequestBuilders.get("/areas/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + id + ","
                        + "\"name\":\"Campground X\","
                        + "\"description\":\"Entire campground\"}"));
    }
}