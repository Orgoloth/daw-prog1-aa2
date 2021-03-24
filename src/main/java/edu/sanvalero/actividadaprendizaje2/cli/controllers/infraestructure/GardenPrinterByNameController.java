package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import java.util.List;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenPrinterByNameController implements Controller {
    private final GardenFinder finder;
    private final GardenPrinter printer;

    private GardenPrinterByNameController(GardenRepository repository) {
        this.finder = new GardenFinder(repository);
        this.printer = new GardenPrinter(repository);
    }

    public static GardenPrinterByNameController create(GardenRepository repository) {
        return new GardenPrinterByNameController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawGardenName = askGardenName();

        List<UUID> results = finder.searchByGardenName(rawGardenName);
        for (UUID rawUuid : results) {
            printer.print(rawUuid);
        }
    }

    private String askGardenName() {
        String rawGardenName = Asker.text("Introduzca el parte del nombre del parque:\t");
        return rawGardenName;
    }

}
