package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.App;
import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create.CreateCityCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class CreateCityController extends Controller {

    public CreateCityController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        String rawName = askCityName();
        String rawRegion = askCityRegion();

        dispatch(new CreateCityCommand(UUID.randomUUID(), rawName, rawRegion));
    }

    private String askCityRegion() {
        System.out.print("Introduzca la comunidad autónoma de la ciudad:\t");
        String rawRegion = App.scanner.nextLine();
        return rawRegion;
    }

    private String askCityName() {
        System.out.print("Introduzca el nombre de la ciudad:\t");
        String rawName = App.scanner.nextLine();
        return rawName;
    }

}
