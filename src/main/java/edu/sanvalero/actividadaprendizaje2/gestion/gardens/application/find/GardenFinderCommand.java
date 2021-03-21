package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;

public class GardenFinderCommand extends Command {
    private String filterCityName;
    private String filterCityRegion;

    public GardenFinderCommand(String filterCityName, String filterCityRegion) {
        this.filterCityName = filterCityName;
        this.filterCityRegion = filterCityRegion;
    }

    public String filterCityName() {
        return filterCityName;
    }

    public String filterCityRegion() {
        return filterCityRegion;
    }
}
