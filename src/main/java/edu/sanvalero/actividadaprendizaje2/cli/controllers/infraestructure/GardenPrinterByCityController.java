package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.List;
import java.util.UUID;

public class GardenPrinterByCityController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByCityController(GardenRepository repository) {
        this.printer = GardenPrinter.create(repository);
    }

    public static GardenPrinterByCityController create(GardenRepository repository) {
        return new GardenPrinterByCityController(repository);
    }

    @Override
    public void invoke() {
        String rawCityName = askCityName();
        printer.printByCityName(rawCityName);
    }

    private String askCityName() {
        return Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
    }
}