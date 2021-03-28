package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print;

import java.util.Collection;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityId;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public class CityPrinter {
    private final CityRepository repository;

    private CityPrinter(CityRepository repository) {
        this.repository = repository;
    }

    public static CityPrinter create(CityRepository repository) {
        return new CityPrinter(repository);
    }

    public void printNameOfCityWithID(UUID id) {
        City city = repository.find(CityId.create(id));
        System.out.println(city.name());
    }

    public void printAll() {
        Collection<City> allCities = repository.all();
        for (City city : allCities) {
            System.out.println(city.id().value() + "\t" + city.name().value() + "\t" + city.region().value());
        }
    }
}
