package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.IntValueObject;

public class GardenExtension extends IntValueObject {

    private GardenExtension(int value) {
        super(value);
    }

    public static GardenExtension create(int rawGardenExtension) {
        return new GardenExtension(rawGardenExtension);
    }

    public GardenExtension add(GardenExtension extension) {
        return GardenExtension.create(value() + extension.value());
    }

}
