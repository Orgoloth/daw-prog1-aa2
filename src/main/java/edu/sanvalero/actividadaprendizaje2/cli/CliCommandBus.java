package edu.sanvalero.actividadaprendizaje2.cli;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandBus;
import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.CommandHandler;

public class CliCommandBus implements CommandBus {
    private Map<Class, CommandHandler> handlers = new HashMap<>();

    @Override
    public void subscribe(CommandHandler handler) {
        handlers.put(getCommandClass(handler), handler);
    }

    @Override
    public void handle(Command command) throws Exception {
        checkHandlerExists(command);
        CommandHandler handler = handlers.get(command.getClass());
        handler.handle(command);
    }

    private void checkHandlerExists(Command command) throws Exception {
        if(!handlers.containsKey(command.getClass())){
            throw new Exception("No hay un handler para " + command.getClass().getName());
        }
    }

    private Class<?> getCommandClass(CommandHandler handler) {
        Type commandInterface = ((ParameterizedType) handler.getClass().getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
        return getClass(commandInterface.getTypeName());
    }

    private Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            return null;
        }
    }

}
