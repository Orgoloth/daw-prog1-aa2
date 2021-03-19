package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.UuidValueObject;

public class GardenId extends UuidValueObject {
    public GardenId(UUID value) {
        super(value);
    }
}
