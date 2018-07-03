package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import it.polimi.ingsw.model.State;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collections;

public class MultiCreateController implements AbstractController {
    private Thread thread;

    public MultiCreateController(){
        ObserverGUI.Singleton().setController(this);
    }

        @FXML
        private AnchorPane mainPane;

        @FXML
        private AnchorPane createPane;

        @FXML
        private Text ErrorMessage;

        @FXML
        private JFXTextField gameName;

        @FXML
        private JFXButton createGame;

        @FXML
        private JFXButton Back;


        public void createGame(ActionEvent actionEvent) {
            try {
                ObserverGUI.Singleton().getServerController().createGame(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton(),gameName.getCharacters().toString());
                ErrorMessage.setText("La partita è stata creata!");
            }
            catch (RemoteException e) {
                ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        }

        public void goBack(ActionEvent actionEvent) {
            try {
                createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }

        @Override
        public void update(State state){
            if(state == State.MATCHNOTSTARTEDYETSTATE){
                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/WaitInterface.fxml"))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
}
