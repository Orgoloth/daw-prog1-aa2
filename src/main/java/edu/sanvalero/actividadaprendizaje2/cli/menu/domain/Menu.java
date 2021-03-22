package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;

public class Menu {
    private MenuName name;
    private MenuDescription description;
    private Controller controller;

    private Menu(MenuName name, MenuDescription description, Controller controller) {
        this.name = name;
        this.controller = controller;
        this.description = description;
    }

    public static Menu create(MenuName name, MenuDescription description, Controller controller) {
        return new Menu(name, description, controller);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menu other = (Menu) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
