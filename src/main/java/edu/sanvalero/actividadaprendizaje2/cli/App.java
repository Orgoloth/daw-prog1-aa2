package edu.sanvalero.actividadaprendizaje2.cli;

import java.util.Scanner;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.CityFinderController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.CreateCityController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.CreateGardenController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.GardenFinderController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.GardenUpdaterController;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuDescription;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuName;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;
import edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure.MenuRepositoryFixed;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create.CityCreator;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create.CreateCityCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find.CityFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find.CityFinderCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure.CityRepositoryPrefilled;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.CreateGardenCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.GardenCreator;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinder;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find.GardenFinderCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinter;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print.GardenPrinterCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update.GardenUpdater;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update.GardenUpdaterCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure.GardenRepositoryMemory;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class App {
    public static Scanner scanner = new Scanner(System.in);

    private MenuRepository menuRepository;
    private CityRepository cityRepository;
    private GardenRepository gardenRepository;

    private CommandBus commandBus;

    public static void main(String[] args) {
        new App();
    }

    private App() {
        bootstrap();
        String userInput = "";
        while (true) {
            try {
                showMenu();
                System.out.print("\nIntroduzca comando o 'salir':\t");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("salir")) {
                    break;
                }
                Controller invokedController = menuRepository.search(new MenuName(userInput)).controller();
                invokedController.invoke();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\n COMANDOS DISPONIBLES:");
        menuRepository.list().forEach((menu) -> {
            System.out.println("\t" + menu.name().value() + "\t" + menu.description().value());
        });
    }

    private void bootstrap() {
        commandBus = new CliCommandBus();

        menuRepository = new MenuRepositoryFixed();
        cityRepository = new CityRepositoryPrefilled();
        gardenRepository = new GardenRepositoryMemory();

        /*
         * Casos del enunciado
         */

        commandBus.subscribe(new CreateGardenCommandHandler(new GardenCreator(gardenRepository, cityRepository)));
        menuRepository.save(
                new Menu(new MenuName("crearparque"), new MenuDescription("Añadir un parque a una determinada ciudad."),
                        new CreateGardenController(commandBus)));

        commandBus.subscribe(new GardenFinderCommandHandler(new GardenFinder(gardenRepository)));
        menuRepository.save(new Menu(new MenuName("listarparques"),
                new MenuDescription("Listar todos los parques (se le preguntarán filtros de ciudad y autonomía)."),
                new GardenFinderController(commandBus)));

        commandBus.subscribe(new GardenUpdaterCommandHandler(new GardenUpdater(gardenRepository)));
        commandBus.subscribe(new GardenPrinterCommandHandler(new GardenPrinter(gardenRepository)));
        menuRepository.save(new Menu(new MenuName("actualizarparque"),
                new MenuDescription("Actualizar los datos de un parque."), new GardenUpdaterController(commandBus)));

        /*
         * Casos auxiliares
         */

        menuRepository.save(new Menu(new MenuName("crearCiudad"), new MenuDescription("Crear una ciudad."),
                new CreateCityController(commandBus)));
        commandBus.subscribe(new CreateCityCommandHandler(new CityCreator(cityRepository)));

        commandBus.subscribe(new CityFinderCommandHandler(new CityFinder(cityRepository)));
        menuRepository.save(new Menu(new MenuName("listarciudades"), new MenuDescription("Listar todas las ciudades."),
                new CityFinderController(commandBus)));

    }
}
