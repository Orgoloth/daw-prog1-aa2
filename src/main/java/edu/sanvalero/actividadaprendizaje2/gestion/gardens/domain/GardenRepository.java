package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import java.util.Collection;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityName;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRegion;

public interface GardenRepository {

    public void save(Garden garden);

    public Garden find(GardenId id) throws GardenNotFound;

    public Collection<Garden> searchByCityName(CityName searchedCityName);

    public Collection<Garden> searchByCityRegion(CityRegion searchedCityRegion);

    public Collection<Garden> searchByGardenName(GardenName searchedGardenName);

    public Collection<Garden> all();

}
