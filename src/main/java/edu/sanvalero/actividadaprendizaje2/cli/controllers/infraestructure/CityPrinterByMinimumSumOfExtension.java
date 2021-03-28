package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.print.CityPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.shared.application.find.CityByGardenExtensionFinder;

import java.util.Collection;
import java.util.UUID;

public class CityPrinterByMinimumSumOfExtension implements Controller {
    private final CityByGardenExtensionFinder cityByGardenExtensionFinder;
    private final CityPrinter printer;

    private CityPrinterByMinimumSumOfExtension(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.cityByGardenExtensionFinder = CityByGardenExtensionFinder.create(gardenRepository);
        this.printer = CityPrinter.create(cityRepository);
    }

    public static CityPrinterByMinimumSumOfExtension create(GardenRepository gardenRepository,
                                                            CityRepository cityRepository) {
        return new CityPrinterByMinimumSumOfExtension(gardenRepository, cityRepository);
    }

    @Override
    public void invoke() {
        int rawMinimumSumGardenExtensionOnCity = askMinimumSumGardenExtensionOnCity();
        Collection<UUID> result = cityByGardenExtensionFinder
                .withGardenIndividualMinimum(rawMinimumSumGardenExtensionOnCity);

        for (UUID id : result) {
            printer.printNameOfCityWithID(id);
        }
    }

    private int askMinimumSumGardenExtensionOnCity() {
        return Asker.number("Introduzca la suma de extensiones mínima dentro de la ciudad:\t");
    }

}
