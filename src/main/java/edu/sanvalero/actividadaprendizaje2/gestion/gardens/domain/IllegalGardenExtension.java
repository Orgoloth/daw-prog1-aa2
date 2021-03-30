package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

public class IllegalGardenExtension extends IllegalArgumentException {
    private IllegalGardenExtension(String reason) {
        super(reason);
    }

    public static IllegalGardenExtension notPositive(int value) {
        return new IllegalGardenExtension("El valor " + value + " no es positivo. Solo se pueden registrar valores positivos no nulos");
    }
}
