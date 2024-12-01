package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.EventBooking;
import mk.finki.ukim.wp.lab.service.EventBookingService;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("currentBooking")
public class EventBookingController {

    private final EventBookingService eventBookingService;
    private final EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }

    @PostMapping("/eventBooking")
    public String createBooking(
            @RequestParam Long eventId,
            @RequestParam String attendeeName,
            @RequestParam String numberofTickets,
            HttpSession session,
            Model model) {


        Event event = eventService.findById(eventId);
        if (event == null) {
            return "redirect:/eventBooking?error=EventNotFound";
        }

        int numberOfTickets = Integer.parseInt(numberofTickets);

        EventBooking booking = eventBookingService.placeBooking(
                event.getName(), attendeeName, event.getLocation().getName(), numberOfTickets);

        session.setAttribute("booking", booking);

        model.addAttribute("booking", booking);

        return "bookingConfirmation";
    }
}



