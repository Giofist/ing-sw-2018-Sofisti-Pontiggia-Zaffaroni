package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SettingsTestController {
    Stage stage = null;
    Parent myNewScene = null;
    @FXML
    private AnchorPane settingsPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXButton Back;

    @FXML
    void goBack(ActionEvent event) {
        stage = (Stage) Back.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Multi");
        stage.show();

    }

}
