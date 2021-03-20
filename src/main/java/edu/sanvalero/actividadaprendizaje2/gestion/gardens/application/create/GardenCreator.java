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
    private final GardenRepository repository;
    private final CityRepository cityRepository;

    public GardenCreator(GardenRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    public void create(UUID id, String rawName, int rawExtension, String cityName) throws Exception {
        try {
            City city = cityRepository.search(new CityName(cityName));
            Garden newGarden = Garden.create(new GardenId(id), new GardenName(rawName),
                    new GardenExtension(rawExtension), city);
            repository.save(newGarden);
        } catch (Exception ex) {
            System.out.println("Error al buscar la ciudad por el nombre: " + cityName + ", no se creará el parque");
        }
    }
}
