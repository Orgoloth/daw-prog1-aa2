package edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenId;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenNotFound;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenRepositoryMemory implements GardenRepository {
    private Map<GardenId, Garden> gardens = new HashMap<>();

    @Override
    public void save(Garden garden) {
        gardens.put(garden.id(), garden);
    }

    @Override
    public void delete(GardenId id) throws GardenNotFound {
        checkGardenExists(id);
        gardens.remove(id);
    }

    @Override
    public Garden find(GardenId id) throws GardenNotFound {
        checkGardenExists(id);
        return gardens.get(id);
    }

    @Override
    public Collection<Garden> searchByCityName(CityName searchedCityName) {
        Collection<Garden> results = new ArrayList<>();

        for (Garden garden : gardens.values()) {
            if (garden.city().name().contains(searchedCityName)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Collection<Garden> searchByCityRegion(CityRegion searchedCityRegion) {
        Collection<Garden> results = new ArrayList<>();

        for (Garden garden : gardens.values()) {
            if (garden.city().region().contains(searchedCityRegion)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Collection<Garden> searchByGardenName(GardenName searchedGardenName) {
        Collection<Garden> results = new ArrayList<>();

        for (Garden garden : gardens.values()) {
            if (garden.name().contains(searchedGardenName)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Collection<Garden> searchByCityNameAndMinimumGardenExtension(CityName searchedCityName,
            GardenExtension minimumGardenExtension) {
        Collection<Garden> results = new ArrayList<>();

        for (Garden garden : gardens.values()) {
            if (garden.city().name().contains(searchedCityName)
                    && garden.extension().isEqualOrBiggerThan(minimumGardenExtension)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Collection<Garden> searchByMinimumExtension(GardenExtension minimumGardenExtension) {
        Collection<Garden> results = new ArrayList<>();

        for (Garden garden : gardens.values()) {
            if (garden.extension().isEqualOrBiggerThan(minimumGardenExtension)) {
                results.add(garden);
            }
        }
        return results;
    }

    @Override
    public Collection<Garden> all() {
        return gardens.values();
    }

    private void checkGardenExists(GardenId id) {
        if (!gardens.containsKey(id)) {
            throw GardenNotFound.withId(id);
        }
    }

}
