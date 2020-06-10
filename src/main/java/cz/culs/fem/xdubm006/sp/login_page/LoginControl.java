package cz.culs.fem.xdubm006.sp.login_page;

import cz.culs.fem.xdubm006.sp.players.PlayerControl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginControl implements Initializable {

    private LoginModel login_model = new LoginModel();
    /**
     * all elements used in .fxml page
     */
    @FXML
    private Label dbstatus_online;
    @FXML
    private Label dbstatus_maintenance;
    @FXML
    private Label dbstatus_offline;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label login_status;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
         * Set up database status to ONLINE/OFFLINE/MAINTENANCE
         */
        if (this.login_model.maintenance_mode == 1 && !this.login_model.isConnected()) {
            this.dbstatus_maintenance.setText("MAINTENANCE");
        }
        if (this.login_model.maintenance_mode == 0 && this.login_model.isConnected()) {
            this.dbstatus_online.setText("ONLINE");
        }
        if (this.login_model.maintenance_mode == 0 && !this.login_model.isConnected()) {
            this.dbstatus_offline.setText("OFFLINE");
        }
        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));
    }

    /**
     * LOGIN login, checks username and password inputted, and goes to admin/student page
     */
    @FXML
    public void Login() {
        try {
            if (this.login_model.isLoggedIn(this.username.getText(), this.password.getText(), this.combobox.getValue().toString())) {
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (this.combobox.getValue().toString()) {
                    case "Admin":
                        AdminLogin();
                        break;
                    case "Player":
                        PlayerLogin();
                        break;
                }
            } else { //wrong password case
                this.login_status.setText("Nope. Try again.");
            }
        } catch (Exception e) { //catching casual errors
            e.printStackTrace();
        }
    }

    public void Exit() { //Red X substitution, when clicked, application returns code 2
        System.exit(2);
    }

    private void PlayerLogin() { //as titled, student login func
        try {
            Stage userStage = new Stage();
            FXMLLoader player_loader = new FXMLLoader();
            Pane student_root = player_loader.load(getClass().getResource("/fxml/player.fxml").openStream());

            PlayerControl players_control = (PlayerControl) player_loader.getController();

            Scene scene = new Scene(student_root);
            userStage.setScene(scene);
            userStage.initStyle(StageStyle.UNDECORATED);
            userStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void AdminLogin() { //as titled again, admin login func
        try {
            Stage adminStage = new Stage();
            FXMLLoader admin_loader = new FXMLLoader();
            Pane admin_root = admin_loader.load(getClass().getResource("/fxml/admin.fxml").openStream());

            //AdminControl admins_control = (AdminControl) admin_loader.getController();

            Scene scene = new Scene(admin_root);
            adminStage.setScene(scene);
            adminStage.initStyle(StageStyle.UNDECORATED);
            adminStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}