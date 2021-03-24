package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import java.util.List;

public interface MenuRepository {
    public void save(Menu menu);

    public Menu find(int position);

    public List<Menu> all();
}
