package mk.finki.ukim.wp.lab.repository.inmemory;

import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryLocationRepository {

    public List<Location> findAll() {
        return DataHolder.locations;
    }
    public Location findById(Long id) {
        return DataHolder.locations.stream()
                .filter(location -> location.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}