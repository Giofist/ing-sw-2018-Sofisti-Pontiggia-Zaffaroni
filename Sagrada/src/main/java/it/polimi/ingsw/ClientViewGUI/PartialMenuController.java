package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collections;

public class PartialMenuController implements AbstractController{

    @FXML
    private Text ErrorMessage;

    @FXML
    private AnchorPane content;

    public PartialMenuController(){
        ObserverGUI.Singleton().setController(this);
    }

    public void setUpMultiPlayer(javafx.event.ActionEvent actionEvent) {
        try {
            content.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitGame(javafx.event.ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().logout(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton());
        } catch (RemoteException e) {
            e.printStackTrace(); //lascio così
        }
        System.exit(0);
    }

    public void setUpSinglePlayer(javafx.event.ActionEvent actionEvent) {
        ErrorMessage.setText("Cooming Soon!");
    }

    @Override
    public void update(State state) {

    }
}