package cz.culs.fem.xdubm006.sp.database_utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String SQCONN = "jdbc:sqlite:tictactoedb.db"; //name of database we are working with

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC"); //sqlite connection type
            return DriverManager.getConnection("jdbc:sqlite:tictactoedb.db"); //url
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

