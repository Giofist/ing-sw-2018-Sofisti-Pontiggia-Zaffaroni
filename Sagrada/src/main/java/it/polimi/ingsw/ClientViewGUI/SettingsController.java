package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collections;

public class SettingsController extends AbstractController {

    SettingsController(){
        ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXButton Back;

    public void goBack(javafx.event.ActionEvent actionEvent) {
        try {
            settingsPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
