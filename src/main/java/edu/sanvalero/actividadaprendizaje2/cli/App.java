package edu.sanvalero.actividadaprendizaje2.cli;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.CityPrinterAllController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenCreatorController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByCityController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByNameController;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.GardenPrinterByRegionController;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.Menu;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuDescription;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuName;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;
import edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure.MenuRepositoryFixed;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure.CityRepositoryPrefilled;
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
        String userInput = "";
        while (true) {
            try {
                showMenu();
                userInput = Asker.text("\nIntroduzca comando o 'salir':\t");
                if (userInput.equalsIgnoreCase("salir")) {
                    break;
                }
                Controller invokedController = menuRepository.find(MenuName.create(userInput)).controller();
                invokedController.invoke();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\n COMANDOS DISPONIBLES:");
        menuRepository.all().forEach((menu) -> {
            System.out.println("\t" + menu.name().value() + "\t" + menu.description().value());
        });
    }

    private void bootstrap() {
        menuRepository = new MenuRepositoryFixed();
        cityRepository = new CityRepositoryPrefilled();
        gardenRepository = new GardenRepositoryMemory();

        menuRepository.save(Menu.create(MenuName.create("listar ciudades"),
                MenuDescription.create("Listar todas las ciudades."), CityPrinterAllController.create(cityRepository)));

        menuRepository.save(Menu.create(MenuName.create("crear parque"), MenuDescription.create("Crear un parque"),
                GardenCreatorController.create(gardenRepository, cityRepository)));

        menuRepository.save(Menu.create(MenuName.create("listar parques ciudad"),
                MenuDescription.create("Listar todos los parques de una ciudad"),
                GardenPrinterByCityController.create(gardenRepository)));

        menuRepository.save(Menu.create(MenuName.create("listar parques region"),
                MenuDescription.create("Listar todos los parques de una region"),
                GardenPrinterByRegionController.create(gardenRepository)));

        menuRepository.save(Menu.create(MenuName.create("listar parques nombre"),
                MenuDescription.create("Listar todos los parques cuyo nombre contenga una determinada cadena"),
                GardenPrinterByNameController.create(gardenRepository)));
    }
}
