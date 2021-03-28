package edu.sanvalero.actividadaprendizaje2.gestion.gardens.infraestructure;

import edu.sanvalero.actividadaprendizaje2.gestion.cities.domain.*;
import edu.sanvalero.actividadaprendizaje2.gestion.gardens.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
    public Garden find(GardenId id) throws GardenNotFound {
        Garden foundGarden = null;
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS WHERE ID=?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, id.toString());
            ResultSet results = sentence.executeQuery();
            if (results.next()) {
                foundGarden = Garden.create(
                        GardenId.create(UUID.fromString(results.getString("ID"))),
                        GardenName.create(results.getString("NAME")),
                        GardenExtension.create(results.getInt("EXTENSION")),
                        cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))))
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return foundGarden;
    }

    @Override
    public void save(Garden garden) {
        String query = "INSERT INTO GARDENS VALUES(?, ?, ?, ?)";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, garden.id().toString());
            sentence.setString(2, garden.name().toString());
            sentence.setInt(3, garden.extension().value());
            sentence.setString(4, garden.city().id().toString());

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
    public Collection<Garden> searchByCityName(CityName searchedCityName) {
        Collection<Garden> founds = new ArrayList<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            ResultSet results = sentence.executeQuery();
            while (results.next()) {
                City candidateCity = cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))));
                if (candidateCity.name().contains(searchedCityName)) {
                    founds.add(Garden.create(
                            GardenId.create(UUID.fromString(results.getString("ID"))),
                            GardenName.create(results.getString("NAME")),
                            GardenExtension.create(results.getInt("EXTENSION")),
                            candidateCity
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return founds;
    }

    @Override
    public Collection<Garden> searchByCityRegion(CityRegion searchedCityRegion) {
        Collection<Garden> founds = new ArrayList<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            ResultSet results = sentence.executeQuery();
            while (results.next()) {
                City candidateCity = cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))));
                if (candidateCity.region().contains(searchedCityRegion)) {
                    founds.add(Garden.create(
                            GardenId.create(UUID.fromString(results.getString("ID"))),
                            GardenName.create(results.getString("NAME")),
                            GardenExtension.create(results.getInt("EXTENSION")),
                            candidateCity
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return founds;
    }

    @Override
    public Collection<Garden> searchByGardenName(GardenName searchedGardenName) {
        Collection<Garden> founds = new ArrayList<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS WHERE UPPER(NAME) LIKE UPPER(?)";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setString(1, "%" + searchedGardenName.toString() + "%");
            ResultSet results = sentence.executeQuery();
            while (results.next()) {
                founds.add(Garden.create(
                        GardenId.create(UUID.fromString(results.getString("ID"))),
                        GardenName.create(results.getString("NAME")),
                        GardenExtension.create(results.getInt("EXTENSION")),
                        cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))))
                ));
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return founds;
    }

    @Override
    public Collection<Garden> searchByCityNameAndMinimumGardenExtension(CityName searchedCityName,
                                                                        GardenExtension minimumGardenExtension) {
        Collection<Garden> founds = new ArrayList<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS WHERE EXTENSION >= ?";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            sentence.setInt(1, minimumGardenExtension.value());
            ResultSet results = sentence.executeQuery();
            while (results.next()) {
                City candidateCity = cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))));
                if (candidateCity.name().contains(searchedCityName)) {
                    founds.add(Garden.create(
                            GardenId.create(UUID.fromString(results.getString("ID"))),
                            GardenName.create(results.getString("NAME")),
                            GardenExtension.create(results.getInt("EXTENSION")), candidateCity
                    ));
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return founds;
    }

    @Override
    public Collection<Garden> searchByMinimumExtension(GardenExtension minimumGardenExtension) {
        throw new UnsupportedOperationException("Oracle garden searchByMinimumExtension Not supported yet.");
    }

    @Override
    public Collection<Garden> all() {
        Collection<Garden> founds = new ArrayList<>();
        String query = "SELECT ID, NAME, EXTENSION, CITY_ID FROM GARDENS";
        try (PreparedStatement sentence = connection.prepareStatement(query)) {
            ResultSet results = sentence.executeQuery();
            while (results.next()) {
                founds.add(Garden.create(
                        GardenId.create(UUID.fromString(results.getString("ID"))),
                        GardenName.create(results.getString("NAME")),
                        GardenExtension.create(results.getInt("EXTENSION")),
                        cityRepository.find(CityId.create(UUID.fromString(results.getString("CITY_ID"))))
                ));
            }
        } catch (SQLException ex) {
            System.out.println("SQL: " + ex.getMessage());
        }
        return founds;
    }
}
