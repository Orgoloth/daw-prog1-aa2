package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.Collection;

public interface CityRepository {
    public void save(City city);

    public City search(CityId id);

    public City search(CityName name) throws Exception;

    public Collection<City> list();
}
