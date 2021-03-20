package edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenId;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenNotFound;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenRepositoryMemory implements GardenRepository {
    private Map<GardenId, Garden> gardens = new HashMap<>();

    @Override
    public void save(Garden garden) {
        gardens.put(garden.id(), garden);
    }

    @Override
    public Garden search(GardenId id) {
        checkGardenExists(id);
        return gardens.get(id);
    }

    @Override
    public Collection<Garden> list() {
        return gardens.values();
    }

    private void checkGardenExists(GardenId id) {
        if (!gardens.containsKey(id)) {
            throw GardenNotFound.withId(id);
        }
    }
}
