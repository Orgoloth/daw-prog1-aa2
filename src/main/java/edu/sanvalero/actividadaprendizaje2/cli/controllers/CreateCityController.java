package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import java.util.Scanner;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create.CreateCityCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class CreateCityController extends Controller {
    private static Scanner scanner = new Scanner(System.in);

    public CreateCityController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        System.out.print("Introduzca el nombre de la ciudad:\t");
        String rawName = scanner.nextLine();
        System.out.print("Introduzca la comunidad autónoma de la ciudad:\t");
        String rawRegion = scanner.nextLine();

        dispatch(new CreateCityCommand(UUID.randomUUID(), rawName, rawRegion));
    }

}
