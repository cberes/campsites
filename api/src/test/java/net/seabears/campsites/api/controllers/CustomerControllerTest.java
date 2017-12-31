package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.data.MockCustomerData;
import net.seabears.campsites.be.dao.CustomerDao;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerDao dao;

    @Test
    public void getCustomers() throws Exception {
        final long id = 1L;
        given(dao.findById(id)).willReturn(MockCustomerData.get(0));

        mvc.perform(MockMvcRequestBuilders.get("/customers/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":" + id + ","
                        + "\"firstName\":\"George\","
                        + "\"lastName\":\"Washington\"}"));
    }
}