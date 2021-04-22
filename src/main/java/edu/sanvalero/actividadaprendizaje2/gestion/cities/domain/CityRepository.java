package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.Set;

public interface CityRepository {
    void save(City city);

    City find(CityId id);

    City findOneOrFailBy(CityName cityName) throws CityNotFound;

    Set<City> searchBy(CityName cityName);

    Set<City> all();
}
