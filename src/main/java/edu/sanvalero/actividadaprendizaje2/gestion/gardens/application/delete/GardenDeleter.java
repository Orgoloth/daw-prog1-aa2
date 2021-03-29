package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.delete;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenDeleter {
    private final GardenRepository repository;
    private final GardenFinder finder;

    private GardenDeleter(GardenRepository repository) {
        this.repository = repository;
        this.finder = GardenFinder.create(repository);
    }

    public static GardenDeleter create(GardenRepository repository) {
        return new GardenDeleter(repository);
    }

    public void deleteBy(String rawCityName) {
        for (Garden garden : finder.searchBy(GardenName.create(rawCityName))) {
            repository.delete(garden.id());
        }
    }
}
