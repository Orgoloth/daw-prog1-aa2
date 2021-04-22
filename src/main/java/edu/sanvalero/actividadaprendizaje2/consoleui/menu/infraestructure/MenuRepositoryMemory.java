package edu.sanvalero.actividadaprendizaje2.consoleui.menu.infraestructure;

import java.util.ArrayList;
import java.util.List;

import edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain.MenuRepository;

public class MenuRepositoryMemory implements MenuRepository {
    private final List<Menu> menuCollection = new ArrayList<>();

    @Override
    public void save(Menu menu) {
        menuCollection.add(menu);
    }

    @Override
    public Menu find(int position) {
        return menuCollection.get(position);
    }

    @Override
    public List<Menu> all() {
        return menuCollection;
    }

}
