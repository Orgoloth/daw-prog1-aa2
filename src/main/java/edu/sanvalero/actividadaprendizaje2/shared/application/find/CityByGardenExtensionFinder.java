package edu.sanvalero.actividadaprendizaje2.shared.application.find;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenExtension;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class CityByGardenExtensionFinder {
    private GardenRepository gardenRepository;

    private CityByGardenExtensionFinder(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public static CityByGardenExtensionFinder create(GardenRepository gardenRepository) {
        return new CityByGardenExtensionFinder(gardenRepository);
    }

    public Collection<UUID> withGardenIndividualMinimum(int gardenIndividualMinimum) {
        Collection<Garden> foundGardens = gardenRepository
                .searchByMinimumExtension(GardenExtension.create(gardenIndividualMinimum));

        Collection<UUID> citiesUuid = new HashSet<>();

        for (Garden garden : foundGardens) {
            citiesUuid.add(garden.city().id().value());
        }

        return citiesUuid;
    }

}
