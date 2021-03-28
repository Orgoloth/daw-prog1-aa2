package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

public class CityNotFound extends IllegalArgumentException {
    private static final long serialVersionUID = -2505453801096539080L;

    public static CityNotFound withId(CityId idNotFound) {
        return new CityNotFound(idNotFound);
    }

    public static CityNotFound withSearchByCityName(CityName cityName) {
        return new CityNotFound(cityName);
    }

    private CityNotFound(CityId idNotFound) {
        super("La ciudad con el id " + idNotFound.value() + " no fue encontrada");
    }

    private CityNotFound(CityName nameNotFound) {
        super("La ciudad con el nombre " + nameNotFound.value() + " no fue encontrada");
    }
}
