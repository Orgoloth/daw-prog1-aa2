package edu.sanvalero.actividadaprendizaje2.db;

public final class OracleDataBase extends DataBaseConnection {

    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String ORACLE_USER = "actividad2";
    private static final String ORACLE_PASSWORD = "actividad2";
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";

    public OracleDataBase() {
        super(ORACLE_DRIVER, ORACLE_USER, ORACLE_PASSWORD, ORACLE_URL);
    }
}
