package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByCityController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByCityController(GardenRepository repository) {
        this.printer = new GardenPrinter(repository);
    }

    public static GardenPrinterByCityController create(GardenRepository repository) {
        return new GardenPrinterByCityController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawCityName = askCityName();
        printer.printByCityName(rawCityName);
    }

    private String askCityName() {
        String rawName = Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
        return rawName;
    }

}
