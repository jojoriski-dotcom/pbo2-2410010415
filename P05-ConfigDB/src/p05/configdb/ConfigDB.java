package p05.configdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    private static final String URL =
        "jdbc:mysql://localhost:3306/db_pbo2_2410010415";

    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASS);
    }
}