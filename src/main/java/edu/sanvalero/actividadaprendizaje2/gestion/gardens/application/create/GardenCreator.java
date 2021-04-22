package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find.CityFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.*;

import java.util.UUID;

public class GardenCreator {
    private final GardenRepository gardenRepository;
    private final CityFinder cityFinder;

    private GardenCreator(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.gardenRepository = gardenRepository;
        this.cityFinder = CityFinder.create(cityRepository, gardenRepository);
    }

    public static GardenCreator create(GardenRepository gardenRepository, CityRepository cityRepository) {
        return new GardenCreator(gardenRepository, cityRepository);
    }

    public void createNewGarden(UUID id, String rawName, int rawExtension, String cityName) {
        City city = cityFinder.findOneOrFailByName(CityName.create(cityName));
        Garden newGarden = Garden.create(
                GardenId.create(id),
                GardenName.create(rawName),
                GardenExtension.create(rawExtension),
                city);
        gardenRepository.save(newGarden);
    }
}
