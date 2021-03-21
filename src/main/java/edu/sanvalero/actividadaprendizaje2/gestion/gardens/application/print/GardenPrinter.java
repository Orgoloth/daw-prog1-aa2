package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import java.util.Iterator;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinter {
    private final GardenRepository repository;

    public GardenPrinter(GardenRepository repository) {
        this.repository = repository;
    }

    public void printByName(String gardenToPrint) {
        Iterator<Garden> it = repository.search().iterator();
        while (it.hasNext()) {
            Garden currentGarden = it.next();
            if (currentGarden.name().value().toUpperCase().contains(gardenToPrint.toUpperCase())) {
                System.out.println(currentGarden.id().value() + "\t" + currentGarden.name().value() + "\t"
                        + currentGarden.extension().value() + "\t" + currentGarden.city().name().value() + "\t"
                        + currentGarden.city().region().value());
            }
        }
    }
}
