package edu.sanvalero.actividadaprendizaje2.consoleui.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.consoleui.Asker;
import edu.sanvalero.actividadaprendizaje2.consoleui.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update.GardenUpdater;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenUpdaterController implements Controller {
    private final GardenPrinter printer;
    private final GardenUpdater updater;

    private GardenUpdaterController(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.printer = GardenPrinter.create(gardenRepository);
        this.updater = GardenUpdater.create(gardenRepository, cityRepository);
    }

    public static GardenUpdaterController create(GardenRepository gardenRepository, CityRepository cityRepository) {
        return new GardenUpdaterController(gardenRepository, cityRepository);
    }

    @Override
    public void invoke() {
        String rawName = askName();
        printer.printOneOrFailBy(rawName);

        String rawNewName = askNewName();
        int rawNewExtension = askNewExtension();
        String rawNewCityName = askNewCityName();
        updater.update(rawName, rawNewName, rawNewExtension, rawNewCityName);

    }

    private String askName() {
        return Asker.text("Introduzca el nombre del parque a modificar:\t");
    }

    private String askNewName() {
        return Asker.text("Introduzca el nuevo nombre del parque (en blanco para no cambiar):\t");
    }

    private String askNewCityName() {
        return Asker.text("Introduzca la nueva ciudad del parque (en blanco para no cambiar):\t");
    }

    private int askNewExtension() {
        return Asker.number("Introduzca la nueva extensión del parque (0 para no cambiar):\t");
    }
}
