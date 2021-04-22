package edu.sanvalero.actividadaprendizaje2.consoleui.controllers.infraestructure;

import edu.sanvalero.actividadaprendizaje2.consoleui.Asker;
import edu.sanvalero.actividadaprendizaje2.consoleui.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.GardenCreator;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.UuidValueObject;

public class GardenCreatorController implements Controller {
    private final GardenCreator creator;

    private GardenCreatorController(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.creator = GardenCreator.create(gardenRepository, cityRepository);
    }

    public static GardenCreatorController create(GardenRepository gardenRepository, CityRepository cityRepository) {
        return new GardenCreatorController(gardenRepository, cityRepository);
    }

    @Override
    public void invoke() {
        String rawName = askName();
        int rawExtension = askExtension();
        String rawCity = askCityName();

        creator.createNewGarden(UuidValueObject.random(), rawName, rawExtension, rawCity);
    }

    private String askName() {
        return Asker.text("Introduzca el nombre del parque:\t");
    }

    private int askExtension() {
        return Asker.number("Introduzca la extensión en km^2 del parque:\t");
    }

    private String askCityName() {
        return Asker.text("Introduzca el nombre de la ciudad:\t");
    }

}
