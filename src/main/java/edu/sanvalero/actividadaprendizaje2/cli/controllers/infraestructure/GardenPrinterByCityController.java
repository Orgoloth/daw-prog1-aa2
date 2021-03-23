package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
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
        //TODO en base a la cadena, recibir ID's concretos que coincidan, desde el finder, y pasar esa lista al printer, separar responsabilidades.
        printer.printByCityName(rawCityName);
    }

    private String askCityName() {
        String rawName = Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
        return rawName;
    }

}
