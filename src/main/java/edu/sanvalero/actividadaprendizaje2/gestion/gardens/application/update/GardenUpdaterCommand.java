package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;

public class GardenUpdaterCommand extends Command {
    private String newName;
    private String newCityName;
    private String newExtension;

    public GardenUpdaterCommand(String newName, String newCityName, String newExtension) {
        this.newName = newName;
        this.newCityName = newCityName;
        this.newExtension = newExtension;
    }

    public String newName() {
        return newName;
    }

    public String newCityName() {
        return newCityName;
    }

    public String newExtension() {
        return newExtension;
    }
}
