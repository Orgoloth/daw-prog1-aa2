package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import java.util.Scanner;
import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.CreateGardenCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class CreateGardenController extends Controller {
    private static Scanner scanner = new Scanner(System.in);

    public CreateGardenController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        System.out.print("Introduzca el nombre del parque:\t");
        String rawName = scanner.nextLine();

        int parsedExtension = Integer.MIN_VALUE;
        do {
            try {
                System.out.print("Introduzca la extensión en km^2 del parque:\t");
                String rawExtension = scanner.nextLine();
                parsedExtension = Integer.parseInt(rawExtension);
            } catch (NumberFormatException ex) {
                System.out.println("Debe introducir un número válido");
            }
        } while (parsedExtension == Integer.MIN_VALUE);

        System.out.print("Introduzca el nombre de la ciudad:\t");
        String rawCity = scanner.nextLine();

        dispatch(new CreateGardenCommand(UUID.randomUUID(), rawName, parsedExtension, rawCity));
    }
}
