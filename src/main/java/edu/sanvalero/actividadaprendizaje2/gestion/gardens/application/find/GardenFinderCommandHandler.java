package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.find;

import java.util.Collection;

import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.Garden;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class GardenFinderCommandHandler implements CommandHandler<GardenFinderCommand> {
    private GardenFinder finder;

    public GardenFinderCommandHandler(GardenFinder finder) {
        this.finder = finder;
    }

    @Override
    public void handle(GardenFinderCommand command) throws Exception {
        Collection<Garden> results = finder.find(command.filterCityName(), command.filterCityRegion());

        // TODO: extraer la impresion a un servicio de aplicacion de impresion
        results.forEach(garden -> {
            System.out.println(garden.id().value() + "\t" + garden.name().value() + "\t" + garden.extension().value()
                    + "\t" + garden.city().name().value() + "\t" + garden.city().region().value());
        });

    }
}
