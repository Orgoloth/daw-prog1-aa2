package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.cli.App;
import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.CreateGardenCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class CreateGardenController extends Controller {

    public CreateGardenController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        String rawName = askName();
        int parsedExtension = askExtension();
        String rawCity = askCityName();

        dispatch(new CreateGardenCommand(UUID.randomUUID(), rawName, parsedExtension, rawCity));
    }

    private String askCityName() {
        System.out.print("Introduzca el nombre de la ciudad:\t");
        String rawCity = App.scanner.nextLine();
        return rawCity;
    }

    private int askExtension() {
        int parsedExtension = Integer.MIN_VALUE;
        do {
            try {
                System.out.print("Introduzca la extensión en km^2 del parque:\t");
                String rawExtension = App.scanner.nextLine();
                parsedExtension = Integer.parseInt(rawExtension);
            } catch (NumberFormatException ex) {
                System.out.println("Debe introducir un número válido");
            }
        } while (parsedExtension == Integer.MIN_VALUE);
        return parsedExtension;
    }

    private String askName() {
        System.out.print("Introduzca el nombre del parque:\t");
        String rawName = App.scanner.nextLine();
        return rawName;
    }
}
