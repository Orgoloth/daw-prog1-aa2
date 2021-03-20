package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import edu.sanvalero.actividadaprendizaje2.cli.Controller;

public class Menu {
    private MenuName name;
    private MenuDescription description;
    private Controller controller;

    public Menu(MenuName name, MenuDescription description, Controller controller) {
        this.name = name;
        this.controller = controller;
        this.description = description;
    }

    public static Menu create(MenuName name, MenuDescription description, Controller controller) {
        return new Menu(name,description, controller);
    }

    public MenuName name() {
        return name;
    }

    public MenuDescription description() {
        return description;
    }

    public Controller controller() {
        return controller;
    }
}
