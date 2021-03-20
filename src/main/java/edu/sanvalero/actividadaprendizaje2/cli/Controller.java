package edu.sanvalero.actividadaprendizaje2.cli;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;

public abstract class Controller {
    protected CommandBus commandBus;

    public Controller(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    protected void dispatch(Command command) throws Exception {
        commandBus.handle(command);
    }

    public abstract void invoke() throws Exception;

}
