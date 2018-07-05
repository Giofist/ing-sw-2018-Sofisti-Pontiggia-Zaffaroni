package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.model.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

/**
 * This class is the GUI for managing multiplayer matches
 */
public class MultiSelectController implements AbstractController{
    @FXML
    private AnchorPane selectPane;

    public MultiSelectController(){
        ObserverGUI.Singleton().setController(this);
    }

    /**
     * Method for invocking the GUI for the creation of the match
     * @param actionEvent
     */
    public void createGame(ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiCreateGame.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for invocking the GUI for joining a new match
     * @param actionEvent
     */
    public void joinGame(ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiJoinGame.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go back to the main menù
     * @param actionEvent
     */
    public void goBack(ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Not implemented because no update is received from the server
     * @param state
     */
    @Override
    public void update(State state) {
    }
}
