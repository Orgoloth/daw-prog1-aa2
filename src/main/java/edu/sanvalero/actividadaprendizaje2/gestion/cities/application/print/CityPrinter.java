package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public class CityPrinter {
    private final CityRepository repository;

    public CityPrinter(CityRepository repository) {
        this.repository = repository;
    }

    public void printAll() {
        repository.all().forEach(city -> {
            System.out.println(city.id().value() + "\t" + city.name().value() + "\t" + city.region().value());
        });
    }

}
