package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByRegionController implements Controller {
    private final GardenPrinter printer;

    private GardenPrinterByRegionController(GardenRepository repository) {
        this.printer = new GardenPrinter(repository);
    }

    public static GardenPrinterByRegionController create(GardenRepository repository) {
        return new GardenPrinterByRegionController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawCityRegion = askCityRegion();
        //TODO en base a la cadena, recibir ID's concretos que coincidan, desde el finder, y pasar esa lista al printer, separar responsabilidades.
        printer.printByCityRegion(rawCityRegion);
    }

    private String askCityRegion() {
        String rawCityRegion = Asker.text("Introduzca el nombre de la región (o parte):\t");
        return rawCityRegion;
    }

}
