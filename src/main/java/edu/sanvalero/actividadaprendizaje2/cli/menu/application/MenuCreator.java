package edu.sanvalero.actividadaprendizaje2.cli.menu.application;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuDescription;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;

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