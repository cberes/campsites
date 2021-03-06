package net.seabears.campsites.api.controllers;

import net.seabears.campsites.be.service.AvailabilityService;
import net.seabears.campsites.enums.Availability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AvailabilityController.class)
public class AvailabilityControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AvailabilityService service;

    @Test
    public void getCampsiteAvailability() throws Exception {
        final long id = 2L;
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        given(service.findByCampsiteId(id, start, end)).willReturn(MockAvailability.get(1, start, end, 2));

        mvc.perform(MockMvcRequestBuilders.get("/availability/campsite/{0}?start={1}&end={2}", id, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"campgroundId\":1,"
                        + "\"campsites\":[{\"id\":" + id + ",\"availability\":"
                        + buildAvailability(start, days) + "}]}"));
    }

    private static String buildAvailability(final LocalDate start, final int days) {
        final StringBuilder builder = new StringBuilder("[");
        for (int day = 0; day < days; ++day) {
            builder.append(day == 0 ? "" : ",")
                    .append("{\"date\":")
                    .append(toJson(start, day))
                    .append(",\"status\":\"")
                    .append(getStatus(day))
                    .append("\"}");
        }
        return builder.append(']').toString();
    }

    private static String toJson(final LocalDate initial, final int dayOffset) {
        final LocalDate date = initial.plusDays(dayOffset);
        return String.format("\"%d-%02d-%02d\"", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    private static Availability getStatus(final int offset) {
        // using the mock service, this assumes offset = 0 is today
        if (offset < 3) {
            return Availability.FIRST_COME_FIRST_SERVE;
        } else if (offset < 8) {
            return Availability.RESERVED;
        } else {
            return Availability.AVAILABLE;
        }
    }

    @ParameterizedTest
    @CsvSource({"area, 1", "campground, 1"})
    public void getAvailability(final String resource, final long id) throws Exception {
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        given(service.findByAreaId(id, start, end)).willReturn(MockAvailability.get(id, start, end, 1, 2));
        given(service.findByCampgroundId(id, start, end)).willReturn(MockAvailability.get(id, start, end, 1, 2));

        mvc.perform(MockMvcRequestBuilders.get("/availability/{0}/{1}?start={2}&end={3}", resource, id, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"campgroundId\":" + id + ",\"campsites\":["
                        + "{\"id\":1,"
                        + "\"availability\":" + buildAvailability(start, days) + "},"
                        + "{\"id\":2,"
                        + "\"availability\":" + buildAvailability(start, days) + "}]}"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground", "campsite"})
    public void getAvailabilityWhenSameDates(final String resource) throws Exception {
        final LocalDate start = LocalDate.now();
        mvc.perform(MockMvcRequestBuilders.get("/availability/{0}/2?start={1}&end={2}", resource, start, start)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad argument for end: must be after start"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground", "campsite"})
    public void getAvailabilityWhenInvalidStartDate(final String resource) throws Exception {
        final String start = "2017-01-0A";
        final LocalDate end = LocalDate.now();
        mvc.perform(MockMvcRequestBuilders.get("/availability/{0}/2?start={1}&end={2}", resource, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Bad argument for start: ")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground", "campsite"})
    public void getAvailabilityWhenInvalidEndDate(final String resource) throws Exception {
        final LocalDate start = LocalDate.now();
        final String end = "2017-01-0A";
        mvc.perform(MockMvcRequestBuilders.get("/availability/{0}/2?start={1}&end={2}", resource, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Bad argument for end: ")));
    }
}
