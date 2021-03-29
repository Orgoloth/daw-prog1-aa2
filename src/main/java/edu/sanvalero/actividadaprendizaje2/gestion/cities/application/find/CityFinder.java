package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityFinder {
    private final CityRepository repository;
    private final GardenFinder gardenFinder;

    private CityFinder(CityRepository repository, GardenRepository gardenRepository) {
        this.repository = repository;
        this.gardenFinder = GardenFinder.create(gardenRepository);
    }

    public static CityFinder create(CityRepository cityRepository, GardenRepository gardenRepository) {
        return new CityFinder(cityRepository, gardenRepository);
    }

    public City searchFirstByName(CityName cityName) {
        return repository.searchBy(cityName).iterator().next();
    }

    public Set<City> searchByMinimumSumGardenExtensionOnCity(GardenExtension filterMinimumSumGardenExtensionOnCity) {
        Set<City> results = new HashSet<>();
        Map<City, GardenExtension> sumExtensionByCity = new HashMap<>();

        for (Garden garden : gardenFinder.all()) {
            if (sumExtensionByCity.containsKey(garden.city())) {
                GardenExtension previousExtension = sumExtensionByCity.get(garden.city());
                sumExtensionByCity.put(garden.city(), previousExtension.add(garden.extension()));
            } else {
                sumExtensionByCity.put(garden.city(), garden.extension());
            }
        }

        for (City city : sumExtensionByCity.keySet()) {
            if (sumExtensionByCity.get(city).isEqualOrBiggerThan(filterMinimumSumGardenExtensionOnCity)) {
                results.add(city);
            }
        }

        return results;
    }

    public Set<City> all() {
        return repository.all();
    }
}
