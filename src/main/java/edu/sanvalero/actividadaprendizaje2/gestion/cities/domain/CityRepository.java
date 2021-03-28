package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.Collection;

public interface CityRepository {
    void save(City city);
    
    City find(CityId id);

    City searchFirstByName(CityName cityName) throws Exception;

    Collection<City> all();
}
