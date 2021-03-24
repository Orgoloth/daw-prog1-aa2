package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.StringValueObject;

public class GardenName extends StringValueObject {

    private GardenName(String value) {
        super(value);
    }

    public static GardenName create(String value) {
        return new GardenName(value);
    }

}
