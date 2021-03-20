package edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command;

public interface CommandHandler<T extends Command> {
    public void handle(T command) throws Exception;
}
