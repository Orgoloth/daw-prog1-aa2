package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.Collection;

public interface CityRepository {
    public void save(City city);
    
    public City find(CityId id);
    
    public City searchByName(CityName name) throws Exception;

    public Collection<City> all();
}
