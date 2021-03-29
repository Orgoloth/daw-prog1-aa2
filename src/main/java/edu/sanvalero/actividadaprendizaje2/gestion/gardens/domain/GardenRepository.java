package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;

import java.util.Set;

public interface GardenRepository {

    void save(Garden garden);

    void delete(GardenId id) throws GardenNotFound;

    Garden find(GardenId id) throws GardenNotFound;

    Set<Garden> searchBy(CityName searchedCityName);

    Set<Garden> searchBy(CityRegion searchedCityRegion);

    Set<Garden> searchBy(GardenName searchedGardenName);

    Set<Garden> searchBy(CityName cityName,
                                GardenExtension minimumGardenExtension);

    Set<Garden> all();

}
