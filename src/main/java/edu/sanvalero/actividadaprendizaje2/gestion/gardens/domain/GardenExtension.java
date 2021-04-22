package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.IntValueObject;

public class GardenExtension extends IntValueObject {

    private GardenExtension(int value) {
        super(value);
        checkPositive(value);
    }

    public static GardenExtension create(int rawGardenExtension) {
        return new GardenExtension(rawGardenExtension);
    }

    private  void checkPositive(int value) throws IllegalGardenExtension {
        if(value<=0){
            throw IllegalGardenExtension.notPositive(value);
        }
    }

    public GardenExtension add(GardenExtension extension) {
        return GardenExtension.create(value() + extension.value());
    }

}
