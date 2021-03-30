package edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GardenRepositoryMemory implements GardenRepository {
    private final Map<GardenId, Garden> gardens = new HashMap<>();

    @Override
    public void save(Garden garden) {
        gardens.put(garden.id(), garden);
    }

    @Override
    public void update(Garden garden) {
        gardens.put(garden.id(), garden);
    }

    @Override
    public void delete(GardenId id) throws GardenNotFound {
        checkGardenExists(id);
        gardens.remove(id);
    }

    @Override
    public Garden findOneOrFailBy(GardenName searchedGardenName) {
        throw GardenNotFound.By(searchedGardenName);
    }

    @Override
    public Set<Garden> searchBy(CityName searchedCityName) {
        Set<Garden> results = new HashSet<>();

        for (Garden garden : gardens.values()) {
            if (garden.city().name().contains(searchedCityName)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Set<Garden> searchBy(CityRegion searchedCityRegion) {
        Set<Garden> results = new HashSet<>();

        for (Garden garden : gardens.values()) {
            if (garden.city().region().contains(searchedCityRegion)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Set<Garden> searchBy(GardenName searchedGardenName) {
        Set<Garden> results = new HashSet<>();

        for (Garden garden : gardens.values()) {
            if (garden.name().contains(searchedGardenName)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Set<Garden> searchBy(CityName searchedCityName,
                                GardenExtension minimumGardenExtension) {
        Set<Garden> results = new HashSet<>();

        for (Garden garden : gardens.values()) {
            if (garden.city().name().contains(searchedCityName)
                    && garden.extension().isEqualOrBiggerThan(minimumGardenExtension)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Set<Garden> all() {
        return new HashSet<>(gardens.values());
    }

    private void checkGardenExists(GardenId id) {
        if (!gardens.containsKey(id)) {
            throw GardenNotFound.withId(id);
        }
    }

}
