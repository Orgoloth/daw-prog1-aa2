package edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuName;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuNotFound;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;

public class MenuRepositoryFixed implements MenuRepository {
    private Map<MenuName, Menu> menuMap = new HashMap<>();

    @Override
    public void save(Menu menu) {
        menuMap.put(menu.name(), menu);
    }

    @Override
    public Menu find(MenuName name) {
        checkMenuExists(name);
        return menuMap.get(name);
    }

    @Override
    public Collection<Menu> all() {
        return menuMap.values();
    }

    private void checkMenuExists(MenuName name) {
        if (!menuMap.containsKey(name)) {
            throw MenuNotFound.withName(name);
        }
    }

}
