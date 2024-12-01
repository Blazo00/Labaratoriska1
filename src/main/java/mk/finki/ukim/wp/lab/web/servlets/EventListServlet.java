package mk.finki.ukim.wp.lab.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "EventListServlet", urlPatterns = "")
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String searchText = req.getParameter("searchText");
        String searchScore = req.getParameter("searchScore");

        List<Event> events;

        if(searchText != null && !searchText.isEmpty()) {
            if(searchScore != null && !searchScore.isEmpty()) {
                try {
                    double score = Double.parseDouble(searchScore);
                    events = eventService.searchEvents(searchText, score);
                } catch (NumberFormatException e) {
                    events = eventService.searchEvents(searchText);
                }
            } else {
                events = eventService.searchEvents(searchText);
            }
        } else {
            events = eventService.listAll();
        }

        context.setVariable("events", events);

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("attendeeName");
        String numberOfTickets = req.getParameter("numberofTickets");

        resp.sendRedirect("/eventBooking?eventName=" + eventName
                + "&attendeeName=" + attendeeName
                + "&numberofTickets=" + numberOfTickets);
    }

}