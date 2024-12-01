//package mk.finki.ukim.wp.lab.web.servlets;
//
//
//import jakarta.servlet.Servlet;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.wp.lab.model.Event;
//import mk.finki.ukim.wp.lab.model.EventBooking;
//import mk.finki.ukim.wp.lab.service.EventBookingService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
//public class EventBookingServlet extends HttpServlet{
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String eventName = req.getParameter("eventName");
//        String attendeeName = req.getParameter("attendeeName");
//        String attendeeAddress = req.getParameter("attendeeAddress");
//        String numTicketsParam = req.getParameter("numberofTickets");
//
//        int numberofTickets = Integer.parseInt(numTicketsParam);
//
//        EventBooking booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numberofTickets);
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("booking", booking);
//        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
//    }
//}
//
//
