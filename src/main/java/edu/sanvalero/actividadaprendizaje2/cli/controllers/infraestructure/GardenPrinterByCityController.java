package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import java.util.List;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByCityController implements Controller {
    private final GardenFinder finder;
    private final GardenPrinter printer;

    private GardenPrinterByCityController(GardenRepository repository) {
        this.finder = new GardenFinder(repository);
        this.printer = new GardenPrinter(repository);
    }

    public static GardenPrinterByCityController create(GardenRepository repository) {
        return new GardenPrinterByCityController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawCityName = askCityName();
        List<UUID> results = finder.searchByCityName(rawCityName);
        for (UUID rawUuid : results) {
            printer.print(rawUuid);
        }
    }

    private String askCityName() {
        String rawName = Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
        return rawName;
    }
}