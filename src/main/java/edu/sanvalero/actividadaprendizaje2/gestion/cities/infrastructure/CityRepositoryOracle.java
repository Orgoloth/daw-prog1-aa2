package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class CityRepositoryOracle implements CityRepository {

    private final Connection connection;

    private CityRepositoryOracle(Connection connection) {
        this.connection = connection;
    }

    public static CityRepositoryOracle create(Connection connection) {
        return new CityRepositoryOracle(connection);
    }

    @Override
    public void save(City city) {
        throw new UnsupportedOperationException("Oracle save City Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public City find(CityId id) {
        City cityFound = null;
        String query = "SELECT ID, NAME, REGION FROM CITIES WHERE ID=?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, id.toString());
            ResultSet results = sentence.executeQuery();
            if (results.next()) {
                cityFound = City.create(
                        CityId.create(UUID.fromString(results.getString("ID"))),
                        CityName.create(results.getString("NAME")),
                        CityRegion.create(results.getString("REGION")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        checkCityWasFound(cityFound, id);
        return cityFound;

    }

    @Override
    public City searchFirstByName(CityName cityName) {
        City cityFound = null;
        String query = "SELECT ID, NAME, REGION FROM CITIES WHERE UPPER(NAME) LIKE UPPER(?)";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, "%" + cityName.value() + "%");
            ResultSet results = sentence.executeQuery();
            if (results.next()) {
                cityFound = City.create(
                        CityId.create(UUID.fromString(results.getString("ID"))),
                        CityName.create(results.getString("NAME")),
                        CityRegion.create(results.getString("REGION")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        checkCityWasFound(cityFound, cityName);
        return cityFound;
    }

    private void checkCityWasFound(City cityFound, CityName cityName) {
        if (null == cityFound) {
            throw CityNotFound.withSearchByCityName(cityName);
        }
    }

    private void checkCityWasFound(City cityFound, CityId cityId) {
        if (null == cityFound) {
            throw CityNotFound.withId(cityId);
        }
    }

    @Override
    public Collection<City> all() {
        Collection<City> all = new ArrayList<>();
        String query = "SELECT ID, NAME, REGION FROM CITIES;";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            ResultSet results = sentence.executeQuery();
            while (results.next()) {
                all.add(City.create(
                        CityId.create(UUID.fromString(results.getString("ID"))),
                        CityName.create(results.getString("NAME")),
                        CityRegion.create(results.getString("REGION"))));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return all;
    }
}
