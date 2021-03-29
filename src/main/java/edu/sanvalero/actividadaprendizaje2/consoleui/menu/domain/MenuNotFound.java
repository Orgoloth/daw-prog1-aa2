package edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain;

public class MenuNotFound extends IllegalArgumentException {
    private static final long serialVersionUID = 5723105624139743919L;

    public static MenuNotFound withDescription(MenuDescription descriptionOFMenuNotFound) {
        return new MenuNotFound(descriptionOFMenuNotFound);
    }

    private MenuNotFound(MenuDescription descriptionOFMenuNotFound) {
        super("La opcion de menu con la descripcion: " + descriptionOFMenuNotFound.value() + " no fue encontrada");
    }
}
