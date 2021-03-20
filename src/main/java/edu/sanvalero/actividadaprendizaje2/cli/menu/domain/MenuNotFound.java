package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

public class MenuNotFound extends IllegalArgumentException {
    private static final long serialVersionUID = 5723105624139743919L;

    public static MenuNotFound withName(MenuName nameNotFound) {
        return new MenuNotFound(nameNotFound);
    }

    private MenuNotFound(MenuName name) {
        super("La opcion de menu con el comando: " + name.value() + " no fue encontrada");
    }
}
