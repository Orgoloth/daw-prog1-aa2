package edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command;

public interface CommandBus {
    public void subscribe(CommandHandler commandHandler);

    public void handle(Command command) throws Exception;
}
