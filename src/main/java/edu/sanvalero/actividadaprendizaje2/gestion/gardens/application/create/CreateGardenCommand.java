package edu.sanvalero.actividadaprendizaje2.gestion.gardens.application.create;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;

public class CreateGardenCommand extends Command {
    private UUID rawUuid;
    private String rawName;
    private int rawExtension;
    private String cityName;

    public CreateGardenCommand(UUID rawUuid, String rawName, int rawExtension, String cityName) {
        this.rawUuid = rawUuid;
        this.rawName = rawName;
        this.rawExtension = rawExtension;
        this.cityName = cityName;
    }

    public UUID rawUuid() {
        return rawUuid;
    }

    public String rawName() {
        return rawName;
    }

    public int rawExtension() {
        return rawExtension;
    }

    public String cityName() {
        return cityName;
    }

}
