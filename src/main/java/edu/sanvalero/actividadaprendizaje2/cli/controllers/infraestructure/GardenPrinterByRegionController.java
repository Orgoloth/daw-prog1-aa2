package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import java.util.List;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByRegionController implements Controller {
    private final GardenFinder finder;
    private final GardenPrinter printer;

    private GardenPrinterByRegionController(GardenRepository repository) {
        this.finder = new GardenFinder(repository);
        this.printer = new GardenPrinter(repository);
    }

    public static GardenPrinterByRegionController create(GardenRepository repository) {
        return new GardenPrinterByRegionController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawCityRegion = askCityRegion();
        List<UUID> results = finder.searchByCityRegion(rawCityRegion);
        for (UUID rawUuid : results) {
            printer.print(rawUuid);
        }
    }

    private String askCityRegion() {
        String rawCityRegion = Asker.text("Introduzca el nombre de la región (o parte):\t");
        return rawCityRegion;
    }

}
