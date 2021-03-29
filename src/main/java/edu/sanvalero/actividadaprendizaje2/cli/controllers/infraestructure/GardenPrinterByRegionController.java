package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.List;
import java.util.UUID;

public class GardenPrinterByRegionController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByRegionController(GardenRepository repository) {
        this.printer = GardenPrinter.create(repository);
    }

    public static GardenPrinterByRegionController create(GardenRepository repository) {
        return new GardenPrinterByRegionController(repository);
    }

    @Override
    public void invoke() {
        String rawCityRegion = askCityRegion();
        printer.printByCityRegion(rawCityRegion);
    }

    private String askCityRegion() {
        return Asker.text("Introduzca el nombre de la región (o parte):\t");
    }

}
