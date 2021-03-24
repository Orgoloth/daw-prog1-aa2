package edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure;

import java.util.ArrayList;
import java.util.List;

import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;

public class MenuRepositoryMemory implements MenuRepository {
    private List<Menu> menuCollection = new ArrayList<>();

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
