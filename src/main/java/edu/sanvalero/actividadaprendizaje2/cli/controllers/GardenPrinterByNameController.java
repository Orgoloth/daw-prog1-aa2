package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByNameController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByNameController(GardenRepository repository) {
        this.printer = new GardenPrinter(repository);
    }

    public static GardenPrinterByNameController create(GardenRepository repository) {
        return new GardenPrinterByNameController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawGardenName = askGardenName();
        printer.printByGardenName(rawGardenName);
    }

    private String askGardenName() {
        String rawGardenName = Asker.text("Introduzca el parte del nombre del parque:\t");
        return rawGardenName;
    }

}
