package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class CreateCityCommandHandler implements CommandHandler<CreateCityCommand> {
    private CityCreator creator;

    public CreateCityCommandHandler(CityCreator creator) {
        this.creator = creator;
    }

    public void handle(CreateCityCommand command) throws Exception {
        creator.create(command.rawUuId(), command.rawName(), command.rawRegion());
    }

}
