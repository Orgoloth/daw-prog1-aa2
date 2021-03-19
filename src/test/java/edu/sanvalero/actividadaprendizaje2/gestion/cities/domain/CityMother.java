package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.UuidValueObject;

public final class CityMother {
    public static City create() {
        return new City(new CityId(UuidValueObject.random()), new CityName("Test city"), new CityRegion("Test region"));
    }
}