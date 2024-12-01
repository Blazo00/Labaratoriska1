package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> searchEvents(String text, double score);
    List<Event> findAllByLocation(Long locationId);
    Event findById(Long id);
    Event save(String name, String description, double popularityScore, Location location);
    Event edit(Long id, String name, String description, double popularityScore, Location location);
    void deleteById(Long id);
}
