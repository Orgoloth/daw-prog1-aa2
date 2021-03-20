package edu.sanvalero.actividadaprendizaje2.cli;

import java.util.Scanner;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.CreateCityController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.CreateGardenController;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuDescription;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuName;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;
import edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure.FixedMenuRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create.CityCreator;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create.CreateCityCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure.CityRepositoryPrefilled;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.CreateGardenCommandHandler;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create.GardenCreator;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure.GardenRepositoryMemory;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public class App {
    private static Scanner scanner = new Scanner(System.in);

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

        menuRepository = new FixedMenuRepository();
        cityRepository = new CityRepositoryPrefilled();
        gardenRepository = new GardenRepositoryMemory();

        menuRepository.save(new Menu(new MenuName("crearCiudad"), new MenuDescription("Crear una ciudad"),
                new CreateCityController(commandBus)));
        menuRepository.save(new Menu(new MenuName("crearParque"), new MenuDescription("Crear un parque en una ciudad"),
                new CreateGardenController(commandBus)));

        commandBus.subscribe(new CreateCityCommandHandler(new CityCreator(cityRepository)));
        commandBus.subscribe(new CreateGardenCommandHandler(new GardenCreator(gardenRepository, cityRepository)));
    }
}
