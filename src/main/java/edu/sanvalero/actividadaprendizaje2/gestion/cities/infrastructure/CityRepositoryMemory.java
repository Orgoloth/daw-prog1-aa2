package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CityRepositoryMemory implements CityRepository {
    private Map<CityId, City> cities = new HashMap<>();

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
    public City searchFirstByName(CityName name) throws CityNotFound {
        for (City city : cities.values()) {
            if (city.name().equals(name)) {
                return city;
            }
        }
        throw CityNotFound.withSearchByCityName(name);
    }

    @Override
    public Collection<City> all() {
        return cities.values();
    }

    private void checkCityExists(CityId id) {
        if (!cities.containsKey(id)) {
            throw CityNotFound.withId(id);
        }
    }

}
