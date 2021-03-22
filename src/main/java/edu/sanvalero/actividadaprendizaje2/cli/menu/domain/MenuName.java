package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.StringValueObject;

public class MenuName extends StringValueObject {
    private MenuName(String value) {
        super(value.toLowerCase());
    }

    public static MenuName create(String value) {
        return new MenuName(value);
    }
}
