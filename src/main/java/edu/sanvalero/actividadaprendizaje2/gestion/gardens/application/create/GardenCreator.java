package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenId;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenCreator {
    private final GardenRepository gardenRepository;
    private final CityRepository cityRepository;

    private GardenCreator(GardenRepository gardenRepository, CityRepository cityRepository) {
        this.gardenRepository = gardenRepository;
        this.cityRepository = cityRepository;
    }

    public static GardenCreator create(GardenRepository gardenRepository, CityRepository cityRepository) {
        return new GardenCreator(gardenRepository, cityRepository);
    }

    public void createNewGarden(UUID id, String rawName, int rawExtension, String cityName) throws Exception {
        try {
            City city = cityRepository.searchByName(CityName.create(cityName));
            Garden newGarden = Garden.create(GardenId.create(id), GardenName.create(rawName),
                    new GardenExtension(rawExtension), city);
            gardenRepository.save(newGarden);
        } catch (Exception ex) {
            System.out.println("Error al buscar la ciudad por el nombre: " + cityName + ", no se creará el parque");
        }
    }
}
