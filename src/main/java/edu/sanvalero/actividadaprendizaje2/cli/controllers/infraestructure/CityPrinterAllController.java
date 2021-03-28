package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print.CityPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public class CityPrinterAllController implements Controller {
    private final CityPrinter printer;

    private CityPrinterAllController(CityRepository repository) {
        this.printer = CityPrinter.create(repository);
    }

    public static CityPrinterAllController create(CityRepository repository) {
        return new CityPrinterAllController(repository);
    }

    @Override
    public void invoke() {
        printer.printAll();
    }
}
