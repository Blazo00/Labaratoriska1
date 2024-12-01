package mk.finki.ukim.wp.lab.repository.inmemory;

import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.bootstrap.DataHolder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryEventRepository {

    public List<Event> findAll() {
        return DataHolder.lista;
    }
    public List<Event> searchEvents(String text) {
        return DataHolder.lista.stream().filter(x -> x.getName().toLowerCase().contains(text.toLowerCase()) ||
                        x.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Event> searchEvents(String text, double score) {
        return DataHolder.lista.stream().filter(x -> x.getName().toLowerCase().contains(text.toLowerCase()) &&
                        x.getPopularityScore() >= score)
                .collect(Collectors.toList());
    }
    public Event findById(Long id) {
        return DataHolder.lista.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Event save(Event event) {
        DataHolder.lista.add(event);
        return event;
    }

    public Event update(Event updatedEvent) {
        deleteById(updatedEvent.getId());
        DataHolder.lista.add(updatedEvent);
        return updatedEvent;
    }

    public void deleteById(Long id) {
        DataHolder.lista.removeIf(event -> event.getId().equals(id));
    }
}
