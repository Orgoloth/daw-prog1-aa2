package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.StringValueObject;

public class MenuDescription extends StringValueObject {

    private MenuDescription(String value) {
        super(value);
    }

    public static MenuDescription create(String value) {
        return new MenuDescription(value);

    }

}
