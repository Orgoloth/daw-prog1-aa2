package edu.sanvalero.actividadaprendizaje2.consoleui.menu.domain;

import java.util.List;

public interface MenuRepository {
    void save(Menu menu);

    Menu find(int position);

    List<Menu> all();
}
