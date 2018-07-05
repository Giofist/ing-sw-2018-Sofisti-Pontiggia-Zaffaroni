package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.State;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collections;

/**
 * This is the controller handling the state in which the user is in the waiting room before the match with a limited set of actions
 */
public class WaitInterfaceController implements AbstractController{

    @FXML
    private AnchorPane createPane;

    @FXML
    private Text ErrorMessage;

    public WaitInterfaceController(){
        ObserverGUI.Singleton().setController(this);
    }

    /**
     * This method handles the situation in which the player wants to leave the match
     * @param actionEvent
     */
    public void leaveTheMatch(javafx.event.ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().leavethematch(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton());
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        try {
            createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is responsible for graphically updating the view based on the player's state
     * @param state The player's state
     */
    @Override
    public void update(State state){
        if(state== State.MUSTSETSCHEMECARD){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    try {
                        createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/ChooseMap.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else if(state == State.ERRORSTATE){
            Platform.runLater(()->{
                ErrorMessage.setText("La partita non può iniziare. C'è stato un errore! ");
                try {
                Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else if(state == State.FORCEENDMATCH){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    try{
                        createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
