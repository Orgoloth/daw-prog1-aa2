package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityId;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityNotFound;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public class CityRepositoryMemory implements CityRepository {
    private Map<CityId, City> cities = new HashMap<>();

    @Override
    public void save(City city) {
        cities.put(city.id(), city);
    }

    @Override
    public City search(CityId id) {
        checkCityExists(id);
        return cities.get(id);
    }

    @Override
    public City search(CityName name) throws Exception {
        Iterator<City> it = cities.values().iterator();
        while (it.hasNext()) {
            City iterationCity = it.next();
            if (iterationCity.name().equals(name)) {
                return iterationCity;
            }
        }
        throw new Exception("La busqueda de ciudad por el nombre: " + name.value() + " no encontró ninguna ciudad");
    }

    @Override
    public Collection<City> find() {
        return cities.values();
    }

    private void checkCityExists(CityId id) {
        if (!cities.containsKey(id)) {
            throw CityNotFound.withId(id);
        }
    }

}
