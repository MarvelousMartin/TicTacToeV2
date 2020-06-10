package cz.culs.fem.xdubm006.sp.players;

//some imports down here

import cz.culs.fem.xdubm006.sp.database_utilities.DatabaseConnection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This is where magic happens - Complete list of all functions, FXML sets and clear/exit addons.
 * <p>
 * All xxxData are very similar, but only CreditData class is commented.
 * Enjoy!
 *
 * @author MartinDub
 */
public class PlayerControl implements Initializable {


    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label logout_message;
    @FXML
    private Button logout;

    /**
     * For every section we need specific sql variable and data
     */
    private DatabaseConnection databaseConnection;

    /**
     * Start of the application
     */
    public PlayerControl() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.databaseConnection = new DatabaseConnection();
        loadStudentInfo();
    }

    public void Exit() { //Substitution for white headbar, implementation of exit button "X"
        Platform.exit();
    }

    /**
     * Student Section - load data from database plus add a new part to it
     *
     * @throws SQLException
     */

    @FXML
    private void loadStudentInfo() {
        this.name.setText("Martin");
        this.surname.setText("Dub");
        this.id.setText("357");
    }

    @FXML
    private void logout() {
        this.logout_message.setText("Logged out!");
        logout.setVisible(false);
    }
}
