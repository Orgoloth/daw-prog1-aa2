package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;

import java.util.*;

public class CityRepositoryMemory implements CityRepository {
    private final Map<CityId, City> cities = new HashMap<>();

    @Override
    public void save(City city) {
        cities.put(city.id(), city);
    }

    @Override
    public City find(CityId id) {
        checkCityExists(id);
        return cities.get(id);
    }

    @Override
    public City findOneOrFailBy(CityName cityName) throws CityNotFound {
        throw CityNotFound.By(cityName);
    }

    @Override
    public Set<City> searchBy(CityName name) {
        return new HashSet<>(cities.values());
    }

    @Override
    public Set<City> all() {
        return new HashSet<>(cities.values());
    }

    private void checkCityExists(CityId id) {
        if (!cities.containsKey(id)) {
            throw CityNotFound.withId(id);
        }
    }

}
