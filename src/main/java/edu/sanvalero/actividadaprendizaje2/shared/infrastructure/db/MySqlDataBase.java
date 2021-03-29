package edu.sanvalero.actividadaprendizaje2.shared.infrastructure.db;

public final class MySqlDataBase extends DataBaseConnection {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_USER = "actividad2";
    private static final String MYSQL_PASSWORD = "actividad2";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/actividad2";

    public MySqlDataBase() {
        super(MYSQL_DRIVER, MYSQL_USER, MYSQL_PASSWORD, MYSQL_URL);
    }
}
