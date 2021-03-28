package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

import java.util.Collection;

public class CityFinder {
    private final CityRepository repository;

    private CityFinder(CityRepository repository) {
        this.repository = repository;
    }

    public static CityFinder create(CityRepository repository){
        return  new CityFinder(repository);
    }

    public City searchFirstByName(CityName cityName) throws Exception {
        return repository.searchFirstByName(cityName);
    }

    public Collection<City> find() {
        return repository.all();
    }
}
