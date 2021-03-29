package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find.CityFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class CityPrinter {
    private final CityFinder finder;

    private CityPrinter(CityRepository cityRepository, GardenRepository gardenRepository) {
        this.finder = CityFinder.create(cityRepository, gardenRepository);
    }

    public static CityPrinter create(CityRepository cityRepository, GardenRepository gardenRepository) {
        return new CityPrinter(cityRepository, gardenRepository);
    }

    public void printAll() {
        for (City city : finder.all()) {
            print(city);
        }
    }

    public void printByMinimumSumGardenExtensionOnCity(int rawMinimumSumGardenExtensionOnCity) {
        for (City city : finder.searchByMinimumSumGardenExtensionOnCity(GardenExtension.create(rawMinimumSumGardenExtensionOnCity))) {
            print(city);
        }
    }

    private void print(City city) {
        System.out.printf("%n %s \t %-30s \t %-30s", city.id(), city.name(), city.region());
    }
}
