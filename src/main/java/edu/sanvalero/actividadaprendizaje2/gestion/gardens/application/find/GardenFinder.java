package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenFinder {
    private final GardenRepository repository;

    private GardenFinder(GardenRepository repository) {
        this.repository = repository;
    }

    public static GardenFinder create(GardenRepository repository) {
        return new GardenFinder(repository);
    }

    public List<UUID> searchByCityName(String rawCityName) {
        Collection<Garden> founds = repository.searchByCityName(CityName.create(rawCityName));
        List<UUID> results = extractUuids(founds);
        return results;
    }

    public List<UUID> searchByGardenName(String rawGardenName) {
        Collection<Garden> founds = repository.searchByGardenName(GardenName.create(rawGardenName));
        List<UUID> results = extractUuids(founds);
        return results;
    }

    public List<UUID> searchByCityRegion(String rawCityRegion) {
        Collection<Garden> founds = repository.searchByCityRegion(CityRegion.create(rawCityRegion));
        List<UUID> results = extractUuids(founds);
        return results;
    }

    public List<UUID> searchByCityNameAndMinimumExtension(String rawCityName, int rawMinimumGardenExtension) {
        Collection<Garden> founds = repository.searchByCityNameAndMinimumGardenExtension(CityName.create(rawCityName),
                GardenExtension.create(rawMinimumGardenExtension));
        List<UUID> results = extractUuids(founds);
        return results;
    }

    private List<UUID> extractUuids(Collection<Garden> founds) {
        List<UUID> results = new ArrayList<>();
        for (Garden garden : founds) {
            results.add(garden.id().value());
        }
        return results;
    }
}
