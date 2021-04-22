package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.StringValueObject;

public class CityName extends StringValueObject {
    private CityName(String value) {
        super(value);
    }

    public static CityName create(String value) {
        return new CityName(value);
    }
}
