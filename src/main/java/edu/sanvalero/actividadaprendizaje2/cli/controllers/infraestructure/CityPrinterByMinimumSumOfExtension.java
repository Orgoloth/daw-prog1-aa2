package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class CityPrinterByMinimumSumOfExtension implements Controller {
    private GardenRepository gardenRepository;

    private CityPrinterByMinimumSumOfExtension(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public static CityPrinterByMinimumSumOfExtension create(GardenRepository gardenRepository) {
        return new CityPrinterByMinimumSumOfExtension(gardenRepository);
    }

    @Override
    public void invoke() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
