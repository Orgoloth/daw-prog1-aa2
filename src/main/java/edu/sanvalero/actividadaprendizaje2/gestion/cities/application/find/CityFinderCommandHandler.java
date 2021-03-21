package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.find;

import java.util.Collection;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.City;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class CityFinderCommandHandler implements CommandHandler<CityFinderCommand> {
    private CityFinder finder;

    public CityFinderCommandHandler(CityFinder finder) {
        this.finder = finder;
    }

    @Override
    public void handle(CityFinderCommand command) throws Exception {
        Collection<City> results = finder.find();
        // TODO: extraer la impresion a un servicio de aplicacion de impresion
        results.forEach(city -> {
            System.out.println(city.id().value() + "\t" + city.name().value() + "\t" + city.region().value());
        });

    }

}
