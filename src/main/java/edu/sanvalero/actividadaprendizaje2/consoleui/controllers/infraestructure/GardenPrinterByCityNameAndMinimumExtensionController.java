package edu.sanvalero.actividadaprendizaje2.consoleui.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.consoleui.Asker;
import edu.sanvalero.actividadaprendizaje2.consoleui.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByCityNameAndMinimumExtensionController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByCityNameAndMinimumExtensionController(GardenRepository repository) {
        this.printer = GardenPrinter.create(repository);
    }

    public static GardenPrinterByCityNameAndMinimumExtensionController create(GardenRepository repository) {
        return new GardenPrinterByCityNameAndMinimumExtensionController(repository);
    }

    @Override
    public void invoke() {
        String rawCityName = askCityName();
        int rawMinimumGardenExtension = askMinimumGardenExtension();

        printer.printCountWithCityNameAndMinimumGardenExtension(rawCityName, rawMinimumGardenExtension);
    }

    private String askCityName() {
        return Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
    }

    private int askMinimumGardenExtension() {
        return Asker.number("Introduzca la extensión mínima:\t");
    }
}
