package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.List;
import java.util.UUID;

public class GardenPrinterByCityNameAndMinimumExtensionController implements Controller {
    private final GardenFinder finder;
    private final GardenPrinter printer;

    private GardenPrinterByCityNameAndMinimumExtensionController(GardenRepository repository) {
        this.finder = GardenFinder.create(repository);
        this.printer = GardenPrinter.create(repository);
    }

    public static GardenPrinterByCityNameAndMinimumExtensionController create(GardenRepository repository) {
        return new GardenPrinterByCityNameAndMinimumExtensionController(repository);
    }

    @Override
    public void invoke() throws Exception {
        String rawCityName = askCityName();
        int rawMinimumGardenExtension = askMinimumGardenExtension();

        List<UUID> results = finder.searchByCityNameAndMinimumExtension(rawCityName, rawMinimumGardenExtension);

        System.out.println("El número de parques de la primera ciudad encontrada con la cadena: " + rawCityName + " es " + results.size());

        // Solo se pide el número, se muestra para confirmar el dato
        // TODO retirar de la versión final
        for (UUID rawUuid : results) {
            printer.print(rawUuid);
        }
    }

    private String askCityName() {
        return Asker.text("Introduzca el nombre de la ciudad (o parte):\t");
    }

    private int askMinimumGardenExtension() {
        return Asker.number("Introduzca la extensión mínima:\t");
    }
}
