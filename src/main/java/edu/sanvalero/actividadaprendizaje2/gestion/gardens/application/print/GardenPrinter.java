package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenId;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinter {
    private final GardenRepository repository;

    private GardenPrinter(GardenRepository repository) {
        this.repository = repository;
    }

    public static GardenPrinter create(GardenRepository repository) {
        return new GardenPrinter(repository);
    }

    public void print(UUID rawUuid) {
        Garden foundGarden = repository.find(GardenId.create(rawUuid));
        System.out.println(foundGarden.id() + "\t" + foundGarden.name() + "\t" + foundGarden.extension() + "\t"
                + foundGarden.city().name() + "\t" + foundGarden.city().region());
    }
}
