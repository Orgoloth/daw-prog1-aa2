package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityId;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public final class CityCreator {
    private final CityRepository repository;

    public CityCreator(CityRepository repository) {
        this.repository = repository;
    }

    public void create(UUID id, String rawName, String rawRegion) {
        City newCity = City.create(CityId.create(id), CityName.create(rawName), CityRegion.create(rawRegion));
        repository.save(newCity);
    }

}
