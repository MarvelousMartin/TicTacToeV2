package cz.culs.fem.xdubm006.sp.login_page;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class LoginApp extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")); //loading graphics

        Scene scene = new Scene(root); //set up scene
        primaryStage.initStyle(StageStyle.UNDECORATED); //to hide white headbar
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
