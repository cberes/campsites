package net.seabears.campsites.api.controllers;

import net.seabears.campsites.api.controllers.exceptions.BadArgumentException;
import net.seabears.campsites.be.domain.CampgroundAvailability;
import net.seabears.campsites.be.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {
    private static final String PARAM_START_DATE = "start";
    private static final String PARAM_END_DATE = "end";

    private final AvailabilityService service;

    @Autowired
    public AvailabilityController(final AvailabilityService service) {
        this.service = service;
    }

    @GetMapping("/campground/{id}")
    public CampgroundAvailability getCampgroundAvailability(@PathVariable final String id,
                                                            @RequestParam(PARAM_START_DATE) final String start,
                                                            @RequestParam(PARAM_END_DATE) final String end) {
        final LocalDate startDate = parseDate(start, PARAM_START_DATE);
        final LocalDate endDate = parseDate(end, PARAM_END_DATE);
        checkPeriod(startDate, endDate);
        return service.findByCampgroundId(id, startDate, endDate);
    }

    private static LocalDate parseDate(final String s, final String name) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new BadArgumentException(name, e.getMessage());
        }
    }

    private static void checkPeriod(final LocalDate start, final LocalDate end) {
        if (!start.isBefore(end)) {
            throw new BadArgumentException(PARAM_END_DATE, "must be after start");
        }
    }

    @GetMapping("/area/{id}")
    public CampgroundAvailability getAreaAvailability(@PathVariable final String id,
                                                      @RequestParam(PARAM_START_DATE) final String start,
                                                      @RequestParam(PARAM_END_DATE) final String end) {
        final LocalDate startDate = parseDate(start, PARAM_START_DATE);
        final LocalDate endDate = parseDate(end, PARAM_END_DATE);
        checkPeriod(startDate, endDate);
        return service.findByAreaId(id, startDate, endDate);
    }

    @GetMapping("/campsite/{id}")
    public CampgroundAvailability getCampsiteAvailability(@PathVariable final String id,
                                                          @RequestParam(PARAM_START_DATE) final String start,
                                                          @RequestParam(PARAM_END_DATE) final String end) {
        final LocalDate startDate = parseDate(start, PARAM_START_DATE);
        final LocalDate endDate = parseDate(end, PARAM_END_DATE);
        checkPeriod(startDate, endDate);
        return service.findByCampsiteId(id, startDate, endDate);
    }
}
