package edu.sanvalero.actividadaprendizaje2.gestion.cities.infrastructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
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
        throw new UnsupportedOperationException("Oracle save City Not supported yet.");
    }

    @Override
    public City find(CityId id) {
        String query = "SELECT ID, NAME, REGION FROM CITIES WHERE ID=?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, id.toString());
            try (ResultSet results = sentence.executeQuery()) {
                if (results.next()) {
                    return City.create(
                            CityId.create(UUID.fromString(results.getString("ID"))),
                            CityName.create(results.getString("NAME")),
                            CityRegion.create(results.getString("REGION")));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        throw CityNotFound.withId(id);

    }

    @Override
    public Set<City> searchBy(CityName cityName) {
        Set<City> found = new HashSet<>();
        String query = "SELECT ID, NAME, REGION FROM CITIES WHERE UPPER(NAME) LIKE UPPER(?)";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, "%" + cityName.value() + "%");
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    found.add(City.create(
                            CityId.create(UUID.fromString(results.getString("ID"))),
                            CityName.create(results.getString("NAME")),
                            CityRegion.create(results.getString("REGION"))));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    @Override
    public Set<City> all() {
        Set<City> all = new HashSet<>();
        String query = "SELECT ID, NAME, REGION FROM CITIES";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    all.add(City.create(
                            CityId.create(UUID.fromString(results.getString("ID"))),
                            CityName.create(results.getString("NAME")),
                            CityRegion.create(results.getString("REGION"))));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return all;
    }
}
