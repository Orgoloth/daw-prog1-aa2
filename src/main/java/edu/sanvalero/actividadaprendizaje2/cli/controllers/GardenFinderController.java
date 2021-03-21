package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import edu.sanvalero.actividadaprendizaje2.cli.App;
import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinderCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class GardenFinderController extends Controller {
    public GardenFinderController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        String filterCityName = askCityFilterName();
        String filterCityRegion = askCityFilterRegion();
        dispatch(new GardenFinderCommand(filterCityName, filterCityRegion));

    }

    private String askCityFilterName() {
        System.out.print("Introduzca la ciudad de los parques que busca (deje en blanco para buscar en todas):\t");
        String filterCityName = App.scanner.nextLine();
        return filterCityName;
    }
    private String askCityFilterRegion() {
        System.out.print("Introduzca la comunidad autónoma de la ciudad de los parques que busca (deje en blanco para buscar en todas):\t");
        String filterCityName = App.scanner.nextLine();
        return filterCityName;
    }

}
