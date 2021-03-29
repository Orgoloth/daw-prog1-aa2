package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;

public class Menu {
    private final MenuDescription description;
    private final Controller controller;

    private Menu(MenuDescription description, Controller controller) {
        this.controller = controller;
        this.description = description;
    }

    public static Menu create(MenuDescription description, Controller controller) {
        return new Menu(description, controller);
    }

    public MenuDescription description() {
        return description;
    }

    public Controller controller() {
        return controller;
    }
}
