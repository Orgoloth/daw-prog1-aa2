package edu.sanvalero.actividadaprendizaje2.cli.menu.domain;

import edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject.StringValueObject;

public class MenuName extends StringValueObject {
    public MenuName(String value) {
        super(value.toLowerCase());
    }
}
