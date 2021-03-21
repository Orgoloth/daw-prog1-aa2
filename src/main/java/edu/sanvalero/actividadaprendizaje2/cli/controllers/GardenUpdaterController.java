package edu.sanvalero.actividadaprendizaje2.cli.controllers;

import edu.sanvalero.actividadaprendizaje2.cli.App;
import edu.sanvalero.actividadaprendizaje2.cli.Controller;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinterCommand;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update.GardenUpdaterCommand;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class GardenUpdaterController extends Controller {

    public GardenUpdaterController(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    public void invoke() throws Exception {
        String gardenToUpdate = askGardenToUpdate();
        dispatch(new GardenPrinterCommand(gardenToUpdate));

        String newName = askNewName();
        String newCityName = askNewCityName();
        String newExtension = askNewExtension();

        dispatch(new GardenUpdaterCommand(newName, newCityName, newExtension));
    }

    private String askGardenToUpdate() {
        System.out.print("Introduzca el nombre del parque que quiere actualizar:\t");
        String gardenToUpdate = App.scanner.nextLine();
        return gardenToUpdate;
    }

    private String askNewName() {
        System.out.print("Introduzca el nuevo nombre del parque:\t");
        String newName = App.scanner.nextLine();
        return newName;
    }

    private String askNewCityName() {
        System.out.print("Introduzca la nueva ciudad del parque:\t");
        String newCityName = App.scanner.nextLine();
        return newCityName;
    }

    private String askNewExtension() {
        System.out.print("Introduzca la nueva extensión del parque:\t");
        String newExtension = App.scanner.nextLine();
        return newExtension;
    }

}
