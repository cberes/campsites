package net.seabears.campsites.controllers;

import net.seabears.campsites.controllers.exceptions.BadArgumentException;
import net.seabears.campsites.domain.CampgroundAvailability;
import net.seabears.campsites.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService service;

    @Autowired
    public AvailabilityController(final AvailabilityService service) {
        this.service = service;
    }

    @GetMapping("/campground/{id}")
    public CampgroundAvailability getCampgroundAvailability(@PathVariable final String id,
                                                            @RequestParam("start") final Date start,
                                                            @RequestParam("end") final Date end) {
        checkPeriod(start, end);
        return service.findAvailabilityForCampground(id, start, end);
    }

    private static void checkPeriod(final Date start, final Date end) {
        if (!start.before(end)) {
            throw new BadArgumentException("end", "must be after start");
        }
    }

    @GetMapping("/area/{id}")
    public CampgroundAvailability getAreaAvailability(@PathVariable final String id,
                                                      @RequestParam("start") final Date start,
                                                      @RequestParam("end") final Date end) {
        checkPeriod(start, end);
        return service.findAvailabilityForArea(id, start, end);
    }

    @GetMapping("/campsite/{id}")
    public CampgroundAvailability getCampsiteAvailability(@PathVariable final String id,
                                                          @RequestParam("start") final Date start,
                                                          @RequestParam("end") final Date end) {
        checkPeriod(start, end);
        return service.findAvailabilityForCampsite(id, start, end);
    }
}
