package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

public class MultiSelectController extends AbstractController{

    MultiSelectController(){
        ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private AnchorPane selectPane;

    public void createGame(ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiCreateGame.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinGame(ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiJoinGame.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
