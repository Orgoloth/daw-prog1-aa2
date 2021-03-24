package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.StringValueObject;

public class CityRegion extends StringValueObject {

    private CityRegion(String value) {
        super(value);
    }

    public static CityRegion create(String value) {
        return new CityRegion(value);
    }
}
