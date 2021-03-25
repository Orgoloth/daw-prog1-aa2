package edu.sanvalero.actividadaprendizaje2.cli;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.CityPrinterAllController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenCreatorController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenDeleterByCity;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByCityController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByCityNameAndMinimumExtensionController;
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

    /**
     * En esta clase preparamos las implementaciones concretas de los repositorios
     */
    private void bootstrap() {
        menuRepository = new MenuRepositoryMemory();
        cityRepository = new CityRepositoryDefault();
        gardenRepository = new GardenRepositoryMemory();

        MenuCreator menuCreator = new MenuCreator(menuRepository);

        menuCreator.create("Listar todos los parques de una determinada ciudad por nombre.",
                GardenPrinterByCityController.create(gardenRepository));

        menuCreator.create("Listar todos los parques de una cierta comunidad autónoma por nombre.",
                GardenPrinterByRegionController.create(gardenRepository));

        menuCreator.create(
                "Añadir un parque a una determinada ciudad (por nombre de ciudad), si la ciudad no existe no se añade y se informa de ello.",
                GardenCreatorController.create(gardenRepository, cityRepository));

        menuCreator.create("(PENDIENTE) Actualizar la información de un parque.", null);

        menuCreator.create("Seleccionar todos los parques cuyo nombre contenga una determinada cadena.",
                GardenPrinterByNameController.create(gardenRepository));

        menuCreator.create(
                "Devolver el número de parques de una determinada ciudad que tengan una extensión individual mayor que la que desee el usuario.",
                GardenPrinterByCityNameAndMinimumExtensionController.create(gardenRepository));

        menuCreator.create("Borrar todos los parques de una determinada ciudad por nombre.", GardenDeleterByCity.create(gardenRepository));

        menuCreator.create(
                "(PENDIENTE) Listar el nombre de todas las ciudades que contengan parques cuya suma total de su extensión, sea mayor que la que quiera el usuario.",
                null);

        menuCreator.create("Listar todas las ciudades.", CityPrinterAllController.create(cityRepository));
        menuCreator.create("Salir.", null);
    }

    private void showMenu() {
        MenuPrinter menuPrinter = new MenuPrinter(menuRepository);
        System.out.println("\n COMANDOS DISPONIBLES:");
        menuPrinter.printAll();
    }
}
