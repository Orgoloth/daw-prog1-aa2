package edu.sanvalero.actividadaprendizaje2.gestion.cities.application.create;

import java.util.UUID;

import edu.sanvalero.actividadaprendizaje2.shared.domain.bus.command.Command;

public class CreateCityCommand extends Command {
    private UUID rawUuId;
    private String rawName;
    private String rawRegion;

    public CreateCityCommand(UUID rawUuId, String rawName, String rawRegion) {
        this.rawUuId = rawUuId;
        this.rawName = rawName;
        this.rawRegion = rawRegion;
    }

    public UUID rawUuId() {
        return rawUuId;
    }

    public String rawName() {
        return rawName;
    }

    public String rawRegion() {
        return rawRegion;
    }

}
