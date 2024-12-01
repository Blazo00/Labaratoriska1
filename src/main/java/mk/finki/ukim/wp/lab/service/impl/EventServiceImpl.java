package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.repository.jpa.EventRepository;
import mk.finki.ukim.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()) ||
                        event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .toList();
    }

    @Override
    public List<Event> searchEvents(String text, double score) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()) &&
                        event.getPopularityScore() >= score)
                .toList();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(String name, String description, double popularityScore, Location location) {
        Event event = new Event(name, description, popularityScore, location);
        return eventRepository.save(event);
    }

    @Override
    public Event edit(Long id, String name, String description, double popularityScore, Location location) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> findAllByLocation(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

}
