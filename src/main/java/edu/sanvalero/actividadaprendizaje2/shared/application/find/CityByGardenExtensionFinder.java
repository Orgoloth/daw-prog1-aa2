package edu.sanvalero.actividadaprendizaje2.shared.application.find;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

import java.util.*;

public class CityByGardenExtensionFinder {
    private final GardenRepository gardenRepository;

    private CityByGardenExtensionFinder(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public static CityByGardenExtensionFinder create(GardenRepository gardenRepository) {
        return new CityByGardenExtensionFinder(gardenRepository);
    }

    public Collection<UUID> withGardenIndividualMinimum(int gardenIndividualMinimum) {
        Collection<UUID> citiesUuid = new HashSet<>();
        Map<City, Integer> sums = new HashMap<>();

        for (Garden garden : gardenRepository.all()) {
            Integer currentSumOfCity = 0;
            if (sums.containsKey(garden.city())) {
                currentSumOfCity = sums.get(garden.city());
            }
            sums.put(garden.city(), currentSumOfCity + garden.extension().value());
        }

        for (City city : sums.keySet()) {
            if (sums.get(city) >= gardenIndividualMinimum) {
                citiesUuid.add(city.id().value());
            }
        }

        return citiesUuid;
    }

}
