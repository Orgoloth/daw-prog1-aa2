package edu.sanvalero.actividadaprendizaje2.cli;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.CityPrinterAllController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenCreatorController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByCityController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByNameController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByRegionController;
import edu.sanvalero.actividadaprendizaje2.cli.menu.application.MenuCreator;
import edu.sanvalero.actividadaprendizaje2.cli.menu.application.MenuPrinter;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;
import edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure.MenuRepositoryMemory;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure.CityRepositoryDefault;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure.GardenRepositoryMemory;

public class App {
    private MenuRepository menuRepository;
    private CityRepository cityRepository;
    private GardenRepository gardenRepository;

    public static void main(String[] args) {
        new App();
    }

    private App() {
        bootstrap();
        while (true) {
            try {
                int userInput;
                showMenu();
                userInput = Asker.number("\nIntroduzca opción:\t");
                Controller invokedController = menuRepository.find(userInput - 1).controller();
                if (invokedController == null) {
                    break;
                }
                invokedController.invoke();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void bootstrap() {
        menuRepository = new MenuRepositoryMemory();
        cityRepository = new CityRepositoryDefault();
        gardenRepository = new GardenRepositoryMemory();

        MenuCreator menuCreator = new MenuCreator(menuRepository);

        menuCreator.create("Listar todas las ciudades.", CityPrinterAllController.create(cityRepository));
        menuCreator.create("Crear un parque.", GardenCreatorController.create(gardenRepository, cityRepository));
        menuCreator.create("Listar todos los parques de una ciudad.",
                GardenPrinterByCityController.create(gardenRepository));
        menuCreator.create("Listar todos los parques de una region.",
                GardenPrinterByRegionController.create(gardenRepository));
        menuCreator.create("Listar todos los parques cuyo nombre contenga una determinada cadena.",
                GardenPrinterByNameController.create(gardenRepository));
        menuCreator.create("Salir.", null);
    }

    private void showMenu() {
        MenuPrinter menuPrinter = new MenuPrinter(menuRepository);
        System.out.println("\n COMANDOS DISPONIBLES:");
        menuPrinter.printAll();
    }
}
