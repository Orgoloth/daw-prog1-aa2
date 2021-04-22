package edu.sanvalero.actividadaprendizaje2.consoleui.menu.application;

import edu.sanvalero.actividadaprendizaje2.consoleui.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain.MenuDescription;
import edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain.MenuRepository;

public class MenuCreator {
    private final MenuRepository repository;

    public MenuCreator(MenuRepository repository) {
        this.repository = repository;
    }

    public void create(String description, Controller controller) {
        Menu newMenu = Menu.create(MenuDescription.create(description), controller);
        repository.save(newMenu);

    }
}