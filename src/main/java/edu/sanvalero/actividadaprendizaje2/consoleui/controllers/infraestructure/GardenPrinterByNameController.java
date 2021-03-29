package edu.sanvalero.actividadaprendizaje2.consoleui.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.consoleui.Asker;
import edu.sanvalero.actividadaprendizaje2.consoleui.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByNameController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByNameController(GardenRepository repository) {
        this.printer = GardenPrinter.create(repository);
    }

    public static GardenPrinterByNameController create(GardenRepository repository) {
        return new GardenPrinterByNameController(repository);
    }

    @Override
    public void invoke() {
        String rawGardenName = askGardenName();
        printer.printByName(rawGardenName);
    }

    private String askGardenName() {
        return Asker.text("Introduzca el parte del nombre del parque:\t");
    }

}
