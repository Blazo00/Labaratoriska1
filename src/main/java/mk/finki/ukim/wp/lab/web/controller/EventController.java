package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.service.EventService;
import mk.finki.ukim.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(
            @RequestParam(required = false) Long locationId,
            Model model) {
        List<Event> events;

        if (locationId != null) {
            events = eventService.findAllByLocation(locationId);
            model.addAttribute("selectedLocationId", locationId);
        } else {
            events = eventService.listAll();
        }

        List<Location> locations = locationService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);

        return "listEvents";
    }


    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("event", new Event());
        return "add-event";
    }

    @GetMapping("/edit/{id}")
    public String showEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id);
        if (event != null) {
            model.addAttribute("event", event);
        } else {
            model.addAttribute("event", new Event());
        }
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    @PostMapping
    public String saveOrUpdateEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double popularityScore,
            @RequestParam Long locationId
    ) {
        Location location = locationService.findById(locationId);
        if (id != null) {
            eventService.edit(id, name, description, popularityScore, location);
        } else {
            eventService.save(name, description, popularityScore, location);
        }
        return "redirect:/events";
    }
}
