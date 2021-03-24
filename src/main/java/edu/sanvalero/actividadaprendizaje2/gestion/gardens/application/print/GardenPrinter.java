package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenId;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinter {
    private final GardenRepository repository;

    public GardenPrinter(GardenRepository repository) {
        this.repository = repository;
    }

    // public void printByCityName(String rawCityName) {
    // CityName cityName = CityName.create(rawCityName);
    // Collection<Garden> results = repository.searchByCityName(cityName);
    // printResults(results);
    // }

    // public void printByCityRegion(String rawCityRegion) {
    // CityRegion cityRegion = CityRegion.create(rawCityRegion);
    // Collection<Garden> results = repository.searchByCityRegion(cityRegion);
    // printResults(results);
    // }

    // public void printByGardenName(String rawGardenName) {
    // GardenName gardenName =GardenName.create(rawGardenName);
    // Collection<Garden> results = repository.searchByGardenName(gardenName);
    // printResults(results);
    // }

    // private void printResults(Collection<Garden> results) {
    // for (Garden garden : results) {
    // System.out.println(garden.id() + "\t" + garden.name() + "\t" +
    // garden.extension() + "\t"
    // + garden.city().name() + "\t" + garden.city().region());
    // }
    // }

    public void print(UUID rawUuid) {
        Garden foundGarden = repository.find(GardenId.create(rawUuid));
        System.out.println(foundGarden.id() + "\t" + foundGarden.name() + "\t" + foundGarden.extension() + "\t"
                + foundGarden.city().name() + "\t" + foundGarden.city().region());
    }
}
