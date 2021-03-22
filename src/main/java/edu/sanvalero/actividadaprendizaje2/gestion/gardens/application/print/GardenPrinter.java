package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinter {
    private final GardenRepository repository;

    public GardenPrinter(GardenRepository repository) {
        this.repository = repository;
    }

    public void printByCityName(String rawCityName) {
        // TODO: Mover logica de filtrado al repositorio
        repository.all().forEach(garden -> {
            if (garden.city().name().value().toLowerCase().contains(rawCityName.toLowerCase()))
                System.out
                        .println(garden.id().value() + "\t" + garden.name().value() + "\t" + garden.extension().value()
                                + "\t" + garden.city().name().value() + "\t" + garden.city().region().value());
        });
    }

    public void printByCityRegion(String rawCityRegion) {
        // TODO: Mover logica de filtrado al repositorio
        repository.all().forEach(garden -> {
            if (garden.city().region().value().toLowerCase().contains(rawCityRegion.toLowerCase()))
                System.out
                        .println(garden.id().value() + "\t" + garden.name().value() + "\t" + garden.extension().value()
                                + "\t" + garden.city().name().value() + "\t" + garden.city().region().value());
        });
    }

    public void printByGardenName(String rawGardenName) {
        // TODO: Mover logica de filtrado al repositorio
        repository.all().forEach(garden -> {
            if (garden.name().value().toLowerCase().contains(rawGardenName.toLowerCase()))
                System.out
                        .println(garden.id().value() + "\t" + garden.name().value() + "\t" + garden.extension().value()
                                + "\t" + garden.city().name().value() + "\t" + garden.city().region().value());
        });
    }
}
