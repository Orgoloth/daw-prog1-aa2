package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find;

import java.util.Collection;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public class CityFinder {
    private final CityRepository repository;

    public CityFinder(CityRepository repository) {
        this.repository = repository;
    }

    public Collection<City> find() {
        return repository.all();
    }
}
