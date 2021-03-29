package edu.sanvalero.actividadaprendizaje2.gestion.cities.domain;

import java.util.Objects;

public final class City {
    private final CityId id;
    private final CityName name;
    private final CityRegion region;

    public City(CityId id, CityName name, CityRegion region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public static City create(CityId id, CityName name, CityRegion region) {
        return new City(id, name, region);
    }

    public CityId id() {
        return id;
    }

    public CityName name() {
        return name;
    }

    public CityRegion region() {
        return region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
