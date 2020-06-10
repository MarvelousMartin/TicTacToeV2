package cz.culs.fem.xdubm006.sp.login_page;

import java.sql.*;

import cz.culs.fem.xdubm006.sp.database_utilities.DatabaseConnection;

public class LoginModel {
    Connection connection;

    int maintenance_mode = 0; //if 1, status is set as MAINTENANCE MODE, else ONLINE

    public LoginModel() { //Database connection
        try {
            this.connection = DatabaseConnection.getConnection(); //connecting to database
        }
        catch (SQLException ex) { //catching SQL errors
            ex.printStackTrace();
        }
        if (this.connection == null) { //if we are not connected, program returns code 100
            System.exit(100);
        }
    }

    public boolean isConnected() { //If there is maintenance mode, we are not connected. Else we are
        if (maintenance_mode == 1) {
            return false;
        }
        return true;
    }

    public boolean isLoggedIn(String user, String pass, String division) throws Exception { //Checks if person already logged in
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM users where username = ? and password = ? and division = ?";

        try {
            preparedStatement = this.connection.prepareStatement(sql); //logging in, checking via SQL
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, division);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            else return false;

        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}

