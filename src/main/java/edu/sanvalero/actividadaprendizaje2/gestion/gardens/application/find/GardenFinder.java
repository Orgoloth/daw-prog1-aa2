package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenName;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.Set;

public class GardenFinder {
    private final GardenRepository repository;

    private GardenFinder(GardenRepository repository) {
        this.repository = repository;
    }

    public static GardenFinder create(GardenRepository repository) {
        return new GardenFinder(repository);
    }

    public Set<Garden> searchBy(CityName filterCityName) {
        return repository.searchBy(filterCityName);
    }

    public Set<Garden> searchBy(GardenName filterGardenName) {
        return repository.searchBy(filterGardenName);
    }

    public Set<Garden> searchBy(CityRegion filterCityRegion) {
        return repository.searchBy(filterCityRegion);
    }

    public Set<Garden> searchBy(CityName filterCityName, GardenExtension filterMinimumGardenExtension) {
        return repository.searchBy(filterCityName, filterMinimumGardenExtension);
    }

    public Set<Garden> all() {
        return repository.all();
    }
}
