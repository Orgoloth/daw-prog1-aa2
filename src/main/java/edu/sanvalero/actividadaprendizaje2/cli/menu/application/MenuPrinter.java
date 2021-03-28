package edu.sanvalero.actividadaprendizaje2.cli.menu.application;

import java.util.List;

import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;

public class MenuPrinter {
    private final MenuRepository repository;

    public MenuPrinter(MenuRepository repository) {
        this.repository = repository;
    }

    public void printAll() {
        List<Menu> menus = repository.all();
        for (int i = 0; i < menus.size(); i++) {
            System.out.println(i + 1 + " .- \t" + menus.get(i).description());
        }
    }
}
