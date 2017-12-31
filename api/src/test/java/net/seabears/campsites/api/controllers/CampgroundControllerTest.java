package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.data.MockAreaData;
import net.seabears.campsites.api.data.MockCampgroundData;
import net.seabears.campsites.api.data.MockCampsiteData;
import net.seabears.campsites.be.dao.AreaDao;
import net.seabears.campsites.be.dao.CampgroundDao;
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
@WebMvcTest(CampgroundController.class)
public class CampgroundControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AreaDao areaDao;

    @MockBean
    private CampgroundDao campgroundDao;

    @MockBean
    private CampsiteDao campsiteDao;

    @Test
    public void getCampgrounds() throws Exception {
        given(campgroundDao.findAll()).willReturn(MockCampgroundData.allData());

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
        given(campgroundDao.findById(id)).willReturn(MockCampgroundData.get(1));

        mvc.perform(MockMvcRequestBuilders.get("/api/campgrounds/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + id + ",\"name\":\"Campground Y\"}"));
    }

    @Test
    public void getAreas() throws Exception {
        final long id = 1L;
        given(areaDao.findByCampgroundId(id)).willReturn(MockAreaData.allData().subList(0, 1));

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
        given(campsiteDao.findByCampgroundId(id)).willReturn(MockCampsiteData.allData());

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