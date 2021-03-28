package edu.sanvalero.actividadaprendizaje2.cli;

import edu.sanvalero.actividadaprendizaje2.cli.controllers.domain.Controller;
import edu.sanvalero.actividadaprendizaje2.cli.controllers.infraestructure.*;
import edu.sanvalero.actividadaprendizaje2.cli.menu.application.MenuCreator;
import edu.sanvalero.actividadaprendizaje2.cli.menu.application.MenuPrinter;
import edu.sanvalero.actividadaprendizaje2.cli.menu.domain.MenuRepository;
import edu.sanvalero.actividadaprendizaje2.cli.menu.infraestructure.MenuRepositoryMemory;
import edu.sanvalero.actividadaprendizaje2.db.DataBaseConnection;
import edu.sanvalero.actividadaprendizaje2.db.OracleDataBase;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.CityRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure.CityRepositoryOracle;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.GardenRepository;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure.GardenRepositoryOracle;

import java.sql.Connection;

public final class App {

    private static DataBaseConnection database;

    private MenuRepository menuRepository;

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

    public static void main(String[] args) {
        database = new OracleDataBase();
        new App();
        database.disconnect();
    }

    /**
     * En esta clase preparamos las implementaciones concretas de los
     * repositorios
     */
    private void bootstrap() {
        Connection connection = database.connection();
        menuRepository = new MenuRepositoryMemory();
        CityRepository cityRepository = CityRepositoryOracle.create(connection);

        GardenRepository gardenRepository = GardenRepositoryOracle.create(connection, cityRepository);

        MenuCreator menuCreator = new MenuCreator(menuRepository);

        menuCreator.create(
                "Listar todos los parques de una determinada ciudad por nombre.",
                GardenPrinterByCityController.create(gardenRepository));

        menuCreator.create(
                "Listar todos los parques de una cierta comunidad autónoma por nombre.",
                GardenPrinterByRegionController.create(gardenRepository));

        menuCreator.create(
                "Añadir un parque a una determinada ciudad (por nombre de ciudad), si la ciudad no existe no se añade y se informa de ello.",
                GardenCreatorController.create(gardenRepository, cityRepository));

        menuCreator.create(
                "(PENDIENTE) Actualizar la información de un parque.",
                null);

        menuCreator.create(
                "Seleccionar todos los parques cuyo nombre contenga una determinada cadena.",
                GardenPrinterByNameController.create(gardenRepository));

        menuCreator.create(
                "Devolver el número de parques de una determinada ciudad que tengan una extensión individual mayor que la que desee el usuario.",
                GardenPrinterByCityNameAndMinimumExtensionController.create(gardenRepository));

        menuCreator.create(
                "Borrar todos los parques de una determinada ciudad por nombre.",
                GardenDeleterByCityName.create(gardenRepository));

        menuCreator.create(
                "Listar el nombre de todas las ciudades que contengan parques cuya suma total de su extensión, sea mayor que la que quiera el usuario.",
                CityPrinterByMinimumSumOfExtension.create(gardenRepository, cityRepository));

        menuCreator.create(
                "(AUX) Listar todas las ciudades.",
                CityPrinterAllController.create(cityRepository));

        menuCreator.create(
                "Salir.",
                null);
    }

    private void showMenu() {
        MenuPrinter menuPrinter = new MenuPrinter(menuRepository);
        System.out.println("\n COMANDOS DISPONIBLES:");
        menuPrinter.printAll();
    }
}
