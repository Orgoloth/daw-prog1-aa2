package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;

public class GardenFinder {
    private final GardenRepository repository;

    public GardenFinder(GardenRepository repository) {
        this.repository = repository;
    }

    public Collection<Garden> find(String filterCityName, String filterCityRegion) {
        // TODO: esta logica habria que pasarla al repositorio

        if (filterCityName.length() == 0 && filterCityRegion.length() == 0) {
            return repository.search();
        }

        Collection<Garden> results = new ArrayList<>();
        Iterator<Garden> it = repository.search().iterator();
        while (it.hasNext()) {
            Garden actualGarden = it.next();
            if ((filterCityName.length() > 0
                    && actualGarden.city().name().value().toUpperCase().contains(filterCityName.toUpperCase()))
                    || (filterCityRegion.length() > 0 && actualGarden.city().region().value().toUpperCase()
                            .contains(filterCityRegion.toUpperCase()))) {
                results.add(actualGarden);
            }
        }
        return results;
    }
}
