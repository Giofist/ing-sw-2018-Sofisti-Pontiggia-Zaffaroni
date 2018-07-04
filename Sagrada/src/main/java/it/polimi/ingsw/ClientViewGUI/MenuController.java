package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.Observable;
import it.polimi.ingsw.model.PlayerState;
import it.polimi.ingsw.model.State;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.ResourceBundle;

public class MenuController implements Initializable,AbstractController{

    @FXML
    private Text PlayerName;

    @FXML
    private Text PlayerScore;

    @FXML
    private Text ErrorMessage;

    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlayerName.setText(ObserverGUI.Singleton().getUsername());
        PlayerScore.setText("");
    }

    public void update(State state){
        Platform.runLater(() -> {
            try {
               content.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MainGameView.fxml"))));
               Observable playerState = new PlayerState();
               playerState.setState(state);
               ObserverGUI.Singleton().update(playerState,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public MenuController(){
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
}
