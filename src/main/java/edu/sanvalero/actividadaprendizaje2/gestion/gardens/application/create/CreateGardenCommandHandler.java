package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class CreateGardenCommandHandler implements CommandHandler<CreateGardenCommand> {
    private GardenCreator creator;

    public CreateGardenCommandHandler(GardenCreator creator) {
        this.creator = creator;
    }

    public void handle(CreateGardenCommand command) throws Exception {
        creator.create(command.rawUuid(), command.rawName(), command.rawExtension(), command.cityName());
    }

}
