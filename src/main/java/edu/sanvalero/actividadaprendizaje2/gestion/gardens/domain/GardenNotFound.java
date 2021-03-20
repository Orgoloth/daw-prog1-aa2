package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

public class GardenNotFound extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public static GardenNotFound withId(GardenId idNotFound) {
        return new GardenNotFound(idNotFound);
    }

    private GardenNotFound(GardenId idNotFound) {
        super("El parque con el id" + idNotFound.value() + " no fue encontrado");
    }
}
