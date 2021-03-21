package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find.CityFinderCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class CityFinderController extends Controller {
    public CityFinderController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        dispatch(new CityFinderCommand());
    }
}
