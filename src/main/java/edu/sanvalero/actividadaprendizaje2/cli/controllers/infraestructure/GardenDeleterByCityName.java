package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import java.util.List;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.delete.GardenDeleter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenDeleterByCityName implements Controller {
    private final GardenFinder finder;
    private final GardenDeleter deleter;

    private GardenDeleterByCityName(GardenRepository repository) {
        this.finder = GardenFinder.create(repository);
        this.deleter = GardenDeleter.create(repository);
    }

    public static GardenDeleterByCityName create(GardenRepository repository) {
        return new GardenDeleterByCityName(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawCityName = askCityName();
        List<UUID> results = finder.searchByCityName(rawCityName);
        for (UUID result : results) {
            deleter.delete(result);
        }
    }

    private String askCityName() {
        return Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
    }
}
