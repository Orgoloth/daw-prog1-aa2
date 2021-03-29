package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print.CityPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class CityPrinterAllController implements Controller {
    private final CityPrinter printer;

    private CityPrinterAllController(CityRepository repository, GardenRepository gardenRepository) {
        this.printer = CityPrinter.create(repository, gardenRepository);
    }

    public static CityPrinterAllController create(CityRepository repository, GardenRepository gardenRepository) {
        return new CityPrinterAllController(repository, gardenRepository);
    }

    @Override
    public void invoke() {
        printer.printAll();
    }
}
