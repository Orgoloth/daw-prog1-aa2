package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenUpdater {
    private final GardenRepository repository;

    public GardenUpdater(GardenRepository repository) {
        this.repository = repository;
    }

}
