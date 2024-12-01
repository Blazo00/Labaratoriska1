package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Location;
import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Location findById(Long id);
}
