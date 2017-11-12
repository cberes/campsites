package net.seabears.campsites.api.controllers;

import net.seabears.campsites.enums.Availability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DataJpaTest
public class AvailabilityControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCampsiteAvailability() throws Exception {
        final UUID id = UUID.fromString("7603ff4e-8515-4e20-be6f-ae3a58669508");
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        mvc.perform(MockMvcRequestBuilders.get("/api/availability/campsite/{0}?start={1}&end={2}", id, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"campgroundId\":\"9cfa88ec-803d-4f22-83b5-af301af9ca96\","
                        + "\"campsites\":[{\"id\":\"" + id + "\",\"availability\":"
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
        return String.format("[%d,%d,%d]", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
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
    @CsvSource({"area, 0f20c7ef-c2cc-4431-85c0-74977fa2de63", "campground, 9cfa88ec-803d-4f22-83b5-af301af9ca96"})
    public void getAvailability(final String resource, final String id) throws Exception {
        final int days = 10;
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusDays(days);
        mvc.perform(MockMvcRequestBuilders.get("/api/availability/{0}/{1}?start={2}&end={3}", resource, id, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"campgroundId\":\"" + id + "\",\"campsites\":["
                        + "{\"id\":\"084bfb46-21cb-4c8c-8a9a-3d0d67002d28\","
                        + "\"availability\":" + buildAvailability(start, days) + "},"
                        + "{\"id\":\"7603ff4e-8515-4e20-be6f-ae3a58669508\","
                        + "\"availability\":" + buildAvailability(start, days) + "}]}"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground", "campsite"})
    public void getAvailabilityWhenSameDates(final String resource) throws Exception {
        final LocalDate start = LocalDate.now();
        mvc.perform(MockMvcRequestBuilders.get("/api/availability/{0}/2?start={1}&end={2}", resource, start, start)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad argument for end: must be after start"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground", "campsite"})
    public void getAvailabilityWhenInvalidStartDate(final String resource) throws Exception {
        final String start = "2017-01-0A";
        final LocalDate end = LocalDate.now();
        mvc.perform(MockMvcRequestBuilders.get("/api/availability/{0}/2?start={1}&end={2}", resource, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Bad argument for start: ")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"area", "campground", "campsite"})
    public void getAvailabilityWhenInvalidEndDate(final String resource) throws Exception {
        final LocalDate start = LocalDate.now();
        final String end = "2017-01-0A";
        mvc.perform(MockMvcRequestBuilders.get("/api/availability/{0}/2?start={1}&end={2}", resource, start, end)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(startsWith("Bad argument for end: ")));
    }
}