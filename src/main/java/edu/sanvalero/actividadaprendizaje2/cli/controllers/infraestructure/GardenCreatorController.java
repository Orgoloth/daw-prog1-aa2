package edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.cli.Asker;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.GardenCreator;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.UuidValueObject;

public class GardenCreatorController implements edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller {
    private final GardenCreator creator;

    private GardenCreatorController(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.creator = new GardenCreator(gardenRepository, cityRepository);
    }

    public static GardenCreatorController create(GardenRepository gardenRepository, CityRepository cityRepository) {
        return new GardenCreatorController(gardenRepository, cityRepository);
    }

    @Override
    public void invoke() throws Exception {
        String rawName = askName();
        int rawExtension = askExtension();
        String rawCity = askCityName();

        creator.create(UuidValueObject.random(), rawName, rawExtension, rawCity);
    }

    private String askName() {
        String rawName = Asker.text("Introduzca el nombre del parque:\t");
        return rawName;
    }

    private int askExtension() {
        int rawExtension = Asker.number("Introduzca la extensión en km^2 del parque:\t");
        return rawExtension;
    }

    private String askCityName() {
        String rawCity = Asker.text("Introduzca el nombre de la ciudad:\t");
        return rawCity;
    }

}
