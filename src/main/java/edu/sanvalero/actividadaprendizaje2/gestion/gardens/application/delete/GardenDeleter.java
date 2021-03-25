package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.delete;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenId;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenNotFound;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenDeleter {
    private final GardenRepository repository;

    private GardenDeleter(GardenRepository repository) {
        this.repository = repository;
    }

    public static GardenDeleter create(GardenRepository repository) {
        return new GardenDeleter(repository);
    }

    public void delete(UUID gardenUuidToDelete) throws GardenNotFound {
        repository.delete(GardenId.create(gardenUuidToDelete));
    }

}
