package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.update;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class GardenUpdaterCommandHandler implements CommandHandler<GardenUpdaterCommand> {
    private GardenUpdater updater;

    public GardenUpdaterCommandHandler(GardenUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void handle(GardenUpdaterCommand command) throws Exception {
        // TODO Auto-generated method stub

    }

}
