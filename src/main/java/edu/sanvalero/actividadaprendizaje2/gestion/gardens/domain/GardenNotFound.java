package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

public class GardenNotFound extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    private GardenNotFound(GardenId idNotFound) {
        super("El parque con el id" + idNotFound.toString() + " no fue encontrado");
    }

    private GardenNotFound(GardenName searchedGardenName) {
        super("El parque con la busqueda por nombre " + searchedGardenName.toString() + " no fue encontrado");
    }

    public static GardenNotFound withId(GardenId idNotFound) {
        return new GardenNotFound(idNotFound);
    }

    public static GardenNotFound By(GardenName searchedGardenName) {
        return new GardenNotFound(searchedGardenName);
    }
}
