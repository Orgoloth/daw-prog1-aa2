package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;

import java.util.*;

public class CityRepositoryDefault implements CityRepository {
    private final Map<CityId, City> cities = new HashMap<>();

    public CityRepositoryDefault() {
        List<String[]> defaultCities = new ArrayList<>();
        defaultCities.add(new String[]{"Zaragoza", "Aragón"});
        defaultCities.add(new String[]{"Huesca", "Aragón"});
        defaultCities.add(new String[]{"Teruel", "Aragón"});
        defaultCities.add(new String[]{"Madrid", "Madrid"});
        defaultCities.add(new String[]{"Sevilla", "Andalucia"});
        defaultCities.add(new String[]{"Malaga", "Andalucia"});
        defaultCities.add(new String[]{"Córdoba", "Andalucia"});

        defaultCities.forEach((data) -> save(
                City.create(CityId.create(UUID.randomUUID()), CityName.create(data[0]), CityRegion.create(data[1]))));

    }

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
    public City searchFirstByName(CityName cityName) {
        for (City city : cities.values()) {
            if (city.name().equals(cityName)) {
                return city;
            }
        }
        throw CityNotFound.withSearchByCityName(cityName);
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
