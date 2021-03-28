package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.UuidValueObject;

public class CityId extends UuidValueObject {

    private CityId(UUID value) {
        super(value);
    }

    public static CityId create(UUID value) {
        return new CityId(value);
    }
}
