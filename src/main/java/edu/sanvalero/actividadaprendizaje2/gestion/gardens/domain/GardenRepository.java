package edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain;

import java.util.Collection;

public interface GardenRepository {

    public void save(Garden garden);

    public Garden search(GardenId id);

    public Collection<Garden> list();
}
