package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collections;

/**
 * This class is the main menù but a lighter version to avoid to reload the background and the top bar
 */
public class PartialMenuController implements AbstractController{

    @FXML
    private Text ErrorMessage;

    @FXML
    private AnchorPane content;

    public PartialMenuController(){
        ObserverGUI.Singleton().setController(this);
    }

    /**
     * Method for accessing the multiplayer section of the gui
     * @param actionEvent
     */
    public void setUpMultiPlayer(javafx.event.ActionEvent actionEvent) {
        try {
            content.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for logging out and exiting from the game
     * @param actionEvent
     */
    public void exitGame(javafx.event.ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().logout(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * Mock method. Single player is not implemented.
     * @param actionEvent
     */
    public void setUpSinglePlayer(javafx.event.ActionEvent actionEvent) {
        ErrorMessage.setText("Cooming Soon!");
    }

    /**
     * Not implemented because no update is received from the server
     * @param state
     */
    @Override
    public void update(State state) {

    }
}