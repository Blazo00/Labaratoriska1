package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.repository.jpa.EventRepository;
import mk.finki.ukim.wp.lab.repository.jpa.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Event> lista = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();

    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;

    public DataHolder(LocationRepository locationRepository, EventRepository eventRepository) {
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    public void init() {
        if (locationRepository.count() == 0) {
            locations = new ArrayList<>();
            locations.add(new Location(null, "Arena Boris Trajkovski", "Londonska", "5000", "Arena"));
            locations.add(new Location(null, "Plostad Aleksandar Makedonski", "Pariska", "300", "Plostad"));
            locations.add(new Location(null, "Ohrid Pristaniste", "Ohridska", "2000", "Pristaniste"));
            locations.add(new Location(null, "Gradski Park", "Ilindenska", "1500", "Park"));
            locations.add(new Location(null, "Rekord", "Partizanska", "400", "Centar"));
            locationRepository.saveAll(locations);
        } else {
            locations = locationRepository.findAll();
        }

        if (eventRepository.count() == 0) {
            lista = new ArrayList<>();
            lista.add(new Event("Pivara Skopje", "Reklamacija na pivo", 8.0, locations.get(0)));
            lista.add(new Event("Pivara Prilep", "Reklamacija na pivo", 9.5, locations.get(1)));
            lista.add(new Event("Vitaminka", "Reklamacija na pivo", 7.6, locations.get(2)));
            lista.add(new Event("Alkaloid", "Reklamacija na pivo", 6.4, locations.get(3)));
            lista.add(new Event("Koncert Memorija", "Reklamacija na pivo", 9.0, locations.get(4)));
            lista.add(new Event("Vlado Janevski", "Reklamacija na pivo", 7.7, locations.get(0)));
            lista.add(new Event("Aca Lukas", "Reklamacija na pivo", 8.0, locations.get(1)));
            lista.add(new Event("Finki Trca", "Reklamacija na pivo", 6.0, locations.get(2)));
            lista.add(new Event("NLB Banka", "Reklamacija na pivo", 10.0, locations.get(3)));
            lista.add(new Event("Makpetrol", "Reklamacija na pivo", 6.5, locations.get(4)));
            eventRepository.saveAll(lista);
        } else {
            lista = eventRepository.findAll();
        }
    }
}
