package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find.CityFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenUpdater {
    private final GardenFinder gardenFinder;
    private final CityFinder cityFinder;
    private final GardenRepository gardenRepository;

    private GardenUpdater(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.gardenRepository = gardenRepository;
        this.gardenFinder = GardenFinder.create(gardenRepository);
        this.cityFinder = CityFinder.create(cityRepository, gardenRepository);
    }

    public static GardenUpdater create(GardenRepository gardenRepository, CityRepository cityRepository) {
        return new GardenUpdater(gardenRepository, cityRepository);
    }

    public void update(String rawName, String rawNewName, int rawNewExtension, String rawNewCityName) {
        Garden gardenToUpdate = gardenFinder.findOneOrFailBy(GardenName.create(rawName));
        if (rawNewName.length() > 0) {
            gardenToUpdate.updateName(GardenName.create(rawNewName));
        }
        if (rawNewExtension != 0) {
            gardenToUpdate.updateExtension(GardenExtension.create(rawNewExtension));
        }
        if (rawNewCityName.length() > 0) {
            gardenToUpdate.updateCity(cityFinder.findOneOrFailByName(CityName.create(rawNewCityName)));
        }
        gardenRepository.update(gardenToUpdate);
    }
}
