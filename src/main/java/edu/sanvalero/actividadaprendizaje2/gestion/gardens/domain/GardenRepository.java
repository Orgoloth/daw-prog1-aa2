package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;

import java.util.Collection;

public interface GardenRepository {

    void save(Garden garden);

    void delete(GardenId id) throws GardenNotFound;

    Garden find(GardenId id) throws GardenNotFound;

    Collection<Garden> searchByCityName(CityName searchedCityName) throws Exception;

    Collection<Garden> searchByCityRegion(CityRegion searchedCityRegion);

    Collection<Garden> searchByGardenName(GardenName searchedGardenName);

    Collection<Garden> searchByCityNameAndMinimumGardenExtension(CityName cityName,
                                                                 GardenExtension minimumGardenExtension);

    Collection<Garden> searchByMinimumExtension(GardenExtension minimumGardenExtension);

    Collection<Garden> all();

}
