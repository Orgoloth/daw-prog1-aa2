package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

public class CityNotFound extends IllegalArgumentException {
    private static final long serialVersionUID = -2505453801096539080L;

    private CityNotFound(CityId idNotFound) {
        super("La ciudad con el id " + idNotFound.value() + " no fue encontrada");
    }

    private CityNotFound(CityName cityNameNotFound) {
        super("La ciudad con el nombre buscado " + cityNameNotFound.toString() + " no fue encontrada");
    }

    public static CityNotFound withId(CityId idNotFound) {
        return new CityNotFound(idNotFound);
    }

    public static CityNotFound By(CityName cityName) {
        return new CityNotFound(cityName);
    }
}
