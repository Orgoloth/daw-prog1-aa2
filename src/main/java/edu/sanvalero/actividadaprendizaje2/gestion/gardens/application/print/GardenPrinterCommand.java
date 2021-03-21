package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;

public class GardenPrinterCommand extends Command {
    private String gardenToPrint;

    public GardenPrinterCommand(String gardenToPrint) {
        this.gardenToPrint = gardenToPrint;
    }

    public String gardenToPrint() {
        return gardenToPrint;
    }

}
