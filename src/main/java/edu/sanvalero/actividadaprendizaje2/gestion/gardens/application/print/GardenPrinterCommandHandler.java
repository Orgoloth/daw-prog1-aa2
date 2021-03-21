package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.print;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class GardenPrinterCommandHandler implements CommandHandler<GardenPrinterCommand> {

    private GardenPrinter printer;

    public GardenPrinterCommandHandler(GardenPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void handle(GardenPrinterCommand command) throws Exception {
        printer.printByName(command.gardenToPrint());

    }

}
