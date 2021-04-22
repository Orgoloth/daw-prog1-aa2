package edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GardenRepositoryOracle implements GardenRepository {
    private final Connection connection;
    private final CityRepository cityRepository;

    private GardenRepositoryOracle(Connection connection, CityRepository cityRepository) {
        this.connection = connection;
        this.cityRepository = cityRepository;
    }

    public static GardenRepository create(Connection connection, CityRepository cityRepository) {
        return new GardenRepositoryOracle(connection, cityRepository);
    }

    @Override
    public void save(Garden garden) {
        String query = "INSERT INTO GARDENS VALUES(?, ?, ?, ?)";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, garden.id().toString());
            sentence.setString(2, garden.name().value());
            sentence.setInt(3, garden.extension().value());
            sentence.setString(4, garden.city().id().toString());

            sentence.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
    }

    @Override
    public void update(Garden garden) {
        String query = "UPDATE GARDENS SET NAME=?, EXTENSION=?, CITY_ID=? WHERE ID=?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, garden.name().value());
            sentence.setInt(2, garden.extension().value());
            sentence.setString(3, garden.city().id().toString());
            sentence.setString(4, garden.id().value().toString());

            sentence.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
    }

    @Override
    public void delete(GardenId id) throws GardenNotFound {
        String query = "DELETE FROM GARDENS WHERE ID=?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, id.toString());
            sentence.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
            throw GardenNotFound.withId(id);
        }
    }

    @Override
    public Garden findOneOrFailBy(GardenName searchedGardenName) {
        Set<Garden> found = searchBy(searchedGardenName);
        checkNotEmpty(found, searchedGardenName);
        return found.iterator().next();
    }

    private void checkNotEmpty(Set<Garden> found, GardenName searchedGardenName) {
        if (found.isEmpty()) {
            throw GardenNotFound.By(searchedGardenName);
        }
    }

    @Override
    public Set<Garden> searchBy(CityName searchedCityName) {
        Set<Garden> found = new HashSet<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    City candidateCity = cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))));
                    if (candidateCity.name().contains(searchedCityName)) {
                        found.add(Garden.create(
                                GardenId.create(UUID.fromString(results.getString("ID"))),
                                GardenName.create(results.getString("NAME")),
                                GardenExtension.create(results.getInt("EXTENSION")),
                                candidateCity
                        ));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return found;
    }

    @Override
    public Set<Garden> searchBy(CityRegion searchedCityRegion) {
        Set<Garden> found = new HashSet<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    City candidateCity = cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))));
                    if (candidateCity.region().contains(searchedCityRegion)) {
                        found.add(Garden.create(
                                GardenId.create(UUID.fromString(results.getString("ID"))),
                                GardenName.create(results.getString("NAME")),
                                GardenExtension.create(results.getInt("EXTENSION")),
                                candidateCity
                        ));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return found;
    }

    @Override
    public Set<Garden> searchBy(GardenName searchedGardenName) {
        Set<Garden> found = new HashSet<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS WHERE UPPER(NAME) LIKE UPPER(?)";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, "%" + searchedGardenName.toString() + "%");
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    found.add(Garden.create(
                            GardenId.create(UUID.fromString(results.getString("ID"))),
                            GardenName.create(results.getString("NAME")),
                            GardenExtension.create(results.getInt("EXTENSION")),
                            cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))))
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return found;
    }

    @Override
    public Set<Garden> searchBy(CityName searchedCityName,
                                GardenExtension minimumGardenExtension) {
        Set<Garden> found = new HashSet<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS WHERE EXTENSION >= ?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setInt(1, minimumGardenExtension.value());
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    City candidateCity = cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))));
                    if (candidateCity.name().contains(searchedCityName)) {
                        found.add(Garden.create(
                                GardenId.create(UUID.fromString(results.getString("ID"))),
                                GardenName.create(results.getString("NAME")),
                                GardenExtension.create(results.getInt("EXTENSION")), candidateCity
                        ));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return found;
    }


    @Override
    public Set<Garden> all() {
        Set<Garden> found = new HashSet<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            try (ResultSet results = sentence.executeQuery()) {
                while (results.next()) {
                    found.add(Garden.create(
                            GardenId.create(UUID.fromString(results.getString("ID"))),
                            GardenName.create(results.getString("NAME")),
                            GardenExtension.create(results.getInt("EXTENSION")),
                            cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))))
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return found;
    }
}
