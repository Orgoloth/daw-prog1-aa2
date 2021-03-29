package edu.sanvalero.actividadaprendizaje2.consoleui.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.consoleui.Asker;
import edu.sanvalero.actividadaprendizaje2.consoleui.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.delete.GardenDeleter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenDeleterByCityNameController implements Controller {
    private final GardenDeleter deleter;

    private GardenDeleterByCityNameController(GardenRepository repository) {
        this.deleter = GardenDeleter.create(repository);
    }

    public static GardenDeleterByCityNameController create(GardenRepository repository) {
        return new GardenDeleterByCityNameController(repository);
    }

    @Override
    public void invoke() {
        String rawCityName = askCityName();
        deleter.deleteByCityName(rawCityName);
    }

    private String askCityName() {
        return Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
    }
}
