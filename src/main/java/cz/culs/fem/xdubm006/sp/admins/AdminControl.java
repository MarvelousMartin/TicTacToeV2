package cz.culs.fem.xdubm006.sp.admins;

//some imports down here
import cz.culs.fem.xdubm006.sp.database_utilities.DatabaseConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This is where magic happens - Complete list of all functions, FXML sets and clear/exit addons.
 *
 * All xxxData are very similar, but only CreditData class is commented.
 * Enjoy!
 *
 * @author MartinDub
 */

public class AdminControl implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private TextField player_id;
    @FXML
    private TextField player_firstname;
    @FXML
    private TextField player_lastname;
    @FXML
    private TextField player_win;
    @FXML
    private TextField player_loss;
    @FXML
    private Label logout_message;
    @FXML
    private Label error;
    @FXML
    private TextField text_id;

    @FXML
    private TableView<PlayerData> playerDataTableView;

    @FXML
    private TableColumn<PlayerData, String> player_idcolumn;
    @FXML
    private TableColumn<PlayerData, String> player_firstnamecolumn;
    @FXML
    private TableColumn<PlayerData, String> player_lastnamecolumn;
    @FXML
    private TableColumn<PlayerData, String> player_wincolumn;
    @FXML
    private TableColumn<PlayerData, String> player_losscolumn;

    /**
     * For every section we need specific sql variable and data
     */
    private DatabaseConnection databaseConnection;
    private ObservableList<PlayerData> player_data;

    private String player_sql = "SELECT * FROM players";

    private String player_name = "SELECT fname FROM players";
    private String player_surname = "SELECT lname FROM players";
    /**
     * Start of the application
     */
    public AdminControl() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.databaseConnection = new DatabaseConnection();
        loadAdminInfo();
    }

    public void Exit() { //Substitution for white headbar, implementation of exit button "X"
        Platform.exit();
    }


    /**
     * Player Section - load data from database plus add a new part to it
     * @param event
     * @throws SQLException
     */
    @FXML
    private void loadPlayerData(ActionEvent event) throws SQLException {
        try {
            Connection connection = DatabaseConnection.getConnection(); //connect to database
            this.player_data = FXCollections.observableArrayList();

            ResultSet player_resultSet = connection.createStatement().executeQuery(player_sql);
            while (player_resultSet.next()) { //load data from database until we have them
                this.player_data.add(new PlayerData(player_resultSet.getString(1), player_resultSet.getString(5), player_resultSet.getString(6), player_resultSet.getString(7), player_resultSet.getString(8)));
                    //cycled adding to table columns
            }

        } catch (SQLException e) { //catching errors
            System.err.println("Error:" + e);
        }

        /*
            Each column in table, where data from database are added
        */
        this.player_idcolumn.setCellValueFactory(new PropertyValueFactory<PlayerData, String>("ID"));
        this.player_firstnamecolumn.setCellValueFactory(new PropertyValueFactory<PlayerData, String>("FirstName"));
        this.player_lastnamecolumn.setCellValueFactory(new PropertyValueFactory<PlayerData, String>("LastName"));
        this.player_wincolumn.setCellValueFactory(new PropertyValueFactory<PlayerData, String>("Wins"));
        this.player_losscolumn.setCellValueFactory(new PropertyValueFactory<PlayerData, String>("Losses"));

        this.playerDataTableView.setItems(null); //reset
        this.playerDataTableView.setItems(this.player_data); //fill
    }

    @FXML
    private void addPlayer(ActionEvent actionEvent) {
        Integer id = 0;
        try {
            id = Integer.parseInt(this.player_id.getText());
        }
        catch (NumberFormatException e){
            this.error.setText("ERROR - ID not a number!");
            return;
        }

        String sqlInsert = "INSERT INTO players(id, fname, lname, Win, Loss) VALUES (?, ?, ?, ?, ?)"; //insert data (in this case id, first name,...) to 5 columns
        String sqlIDCheck = String.format("SELECT * FROM players WHERE id = %d", id);
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement player_preparedStatement = connection.prepareStatement(sqlInsert);
            PreparedStatement IDcheck_preparedStatement = connection.prepareStatement(sqlIDCheck);

            ResultSet rs = IDcheck_preparedStatement.executeQuery();

            if(this.player_id.getText().isEmpty() ||this.player_firstname.getText().isEmpty() ||
               this.player_lastname.getText().isEmpty() || this.player_win.getText().isEmpty() ||
               this.player_loss.getText().isEmpty()) {

                this.error.setText("ERROR - TextField cannot be empty!");

            }
            else if(!rs.isClosed()) {
                this.error.setText("ERROR - ID already exists!");
            }
            else {

                /*
                    Put text into TextField, this text uploads to database, then show up in the table
                 */
                player_preparedStatement.setString(1, id.toString());
                player_preparedStatement.setString(2, this.player_firstname.getText());
                player_preparedStatement.setString(3, this.player_lastname.getText());
                player_preparedStatement.setString(4, this.player_win.getText());
                player_preparedStatement.setString(5, this.player_loss.getText());

                player_preparedStatement.execute();
                this.error.setText("");
            }
            connection.close();


        } catch (SQLException e) { //catching SQL errors
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        this.logout_message.setText("Logged out!");
        logout.setVisible(false);
    }

    @FXML
    private void loadAdminInfo() {
        this.name.setText(player_name);
        this.surname.setText(player_surname);
    }

    /**
     * Clear up mess from TextFields
     * @param actionEvent
     */

    @FXML
    private void clear(ActionEvent actionEvent) {
        this.player_id.setText("");
        this.player_firstname.setText("");
        this.player_lastname.setText("");
    }

}
