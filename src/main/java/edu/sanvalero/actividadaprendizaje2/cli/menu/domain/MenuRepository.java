package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import java.util.Collection;

public interface MenuRepository {
    public void save(Menu menu);

    public Menu search(MenuName command);

    public Collection<Menu> list();
}
