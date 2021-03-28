package edu.sanvalero.actividadaprendizaje2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String ORACLE_USER = "HR";
    private final String ORACLE_PASSWORD = "hr";
    private final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
  

    private final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String MYSQL_USER = "actividad2";
    private final String MYSQL_PASSWORD = "actividad2";
    private final String MYSQL_URL = "jdbc:mysql://localhost:3306/actividad2";

    public static enum PROVIDER {
        MYSQL, ORACLE
    }

    private Connection connection;

    public DataBaseConnection(PROVIDER provider) {
        String driver = "";
        String url = "";
        String user = "";
        String password = "";

        switch (provider) {
        case MYSQL:
            driver = MYSQL_DRIVER;
            url = MYSQL_URL;
            user = MYSQL_USER;
            password = MYSQL_PASSWORD;
            break;
        case ORACLE:
            driver = ORACLE_DRIVER;
            url = ORACLE_URL;
            user = ORACLE_USER;
            password = ORACLE_PASSWORD;
            break;
        }

        try {
            Class.forName(driver);
            System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            connection = DriverManager.getConnection(url, user, password);
            // connection = DriverManager.getConnection(url);
            System.out.println("conectado");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection connection() {
        return connection;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
