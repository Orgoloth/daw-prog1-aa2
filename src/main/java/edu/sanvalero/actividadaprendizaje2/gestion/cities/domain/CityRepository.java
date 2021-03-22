package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.Collection;

public interface CityRepository {
    public void save(City city);

    public City find(CityName name) throws Exception;

    public City search(CityId id);

    public Collection<City> all();
}
