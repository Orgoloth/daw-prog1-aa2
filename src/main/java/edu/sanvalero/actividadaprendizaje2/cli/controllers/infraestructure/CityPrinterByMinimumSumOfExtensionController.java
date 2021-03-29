package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print.CityPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class CityPrinterByMinimumSumOfExtensionController implements Controller {
    private final CityPrinter printer;

    private CityPrinterByMinimumSumOfExtensionController(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.printer = CityPrinter.create(cityRepository, gardenRepository);
    }

    public static CityPrinterByMinimumSumOfExtensionController create(GardenRepository gardenRepository,
                                                                      CityRepository cityRepository) {
        return new CityPrinterByMinimumSumOfExtensionController(gardenRepository, cityRepository);
    }

    @Override
    public void invoke() {
        int rawMinimumSumGardenExtensionOnCity = askMinimumSumGardenExtensionOnCity();
        printer.printByMinimumSumGardenExtensionOnCity(rawMinimumSumGardenExtensionOnCity);
    }

    private int askMinimumSumGardenExtensionOnCity() {
        return Asker.number("Introduzca la suma de extensiones mínima dentro de la ciudad:\t");
    }

}
