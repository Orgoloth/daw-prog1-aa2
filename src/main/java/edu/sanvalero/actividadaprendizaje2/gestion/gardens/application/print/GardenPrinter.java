package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.Set;

public class GardenPrinter {
    private final GardenFinder finder;

    private GardenPrinter(GardenRepository repository) {
        this.finder = GardenFinder.create(repository);
    }

    public static GardenPrinter create(GardenRepository repository) {
        return new GardenPrinter(repository);
    }

    public void printOneOrFailBy(String rawGardenName) {
        Garden garden = finder.findOneOrFailBy(GardenName.create(rawGardenName));
        print(garden);
    }

    public void printByName(String rawGardenName) {

        for (Garden garden : finder.searchBy(GardenName.create(rawGardenName))) {
            print(garden);
        }
    }

    public void printByCityName(String rawCityName) {
        for (Garden garden : finder.searchBy(CityName.create(rawCityName))) {
            print(garden);
        }
    }

    public void printByCityRegion(String rawCityRegion) {
        for (Garden garden : finder.searchBy(CityRegion.create(rawCityRegion))) {
            print(garden);
        }
    }

    public void printCountWithCityNameAndMinimumGardenExtension(String rawCityName, int rawMinimumGardenExtension) {
        Set<Garden> gardens = finder.searchBy(CityName.create(rawCityName), GardenExtension.create(rawMinimumGardenExtension));
        printCount(gardens, rawCityName, rawMinimumGardenExtension);
    }

    private void print(Garden garden) {
        System.out.printf("%n %s \t %-30s \t %s \t %-30s \t %s", garden.id(), garden.name(), garden.extension(), garden.city().name(), garden.city().region());
    }

    private void printCount(Set<Garden> gardens, String rawCityName, int rawMinimumGardenExtension) {
        System.out.printf("Se han encontrado %d jardines en %s con un mínimo de %d de extensión.%n", gardens.size(), rawCityName, rawMinimumGardenExtension);
    }
}
