package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityId;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityNotFound;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;

public class CityRepositoryPrefilled implements CityRepository {
    private Map<CityId, City> cities = new HashMap<>();

    public CityRepositoryPrefilled() {
        List<String[]> defaultCities = new ArrayList<>();
        defaultCities.add(new String[] { "Zaragoza", "Aragón" });
        defaultCities.add(new String[] { "Huesca", "Aragón" });
        defaultCities.add(new String[] { "Teruel", "Aragón" });
        defaultCities.add(new String[] { "Madrid", "Madrid" });
        defaultCities.add(new String[] { "Sevilla", "Andalucia" });
        defaultCities.add(new String[] { "Malaga", "Andalucia" });
        defaultCities.add(new String[] { "Córdoba", "Andalucia" });

        defaultCities.forEach((data) -> save(
                City.create(new CityId(UUID.randomUUID()), new CityName(data[0]), new CityRegion(data[1]))));

    }

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
    public Collection<City> list() {
        return cities.values();
    }

    private void checkCityExists(CityId id) {
        if (!cities.containsKey(id)) {
            throw CityNotFound.withId(id);
        }
    }

}
