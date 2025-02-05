package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.State;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

/**
 * Class for joining to existent matches
 */
public class MultiJoinController  implements Initializable,AbstractController{
    private Boolean entered = false;
    ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    private AnchorPane joinPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXListView<String> gameList;

    public MultiJoinController(){
        ObserverGUI.Singleton().setController(this);
    }

    /**
     * This method is for downloading the the list of available matches
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           for (Match match : ObserverGUI.Singleton().getServerController().getActiveMatchesList()) {
               data.add(match.getName());
               gameList.setItems(data);
           }
        } catch (RemoteException e) {
           ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
    }

    /**
     * This method is for trying to join the selected match
     * @param actionEvent
     */
    public void joinGame(ActionEvent actionEvent) {
        String matchName;
        matchName = gameList.getSelectionModel().getSelectedItem();
        if (matchName != null) {
            try {
                ErrorMessage.setText(gameList.getSelectionModel().getSelectedItem());
                ObserverGUI.Singleton().getServerController().joinaMatch(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton(), matchName);
                entered = true;
                try {
                    joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/WaitInterface.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (RemoteException e) {
                ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        }
        else ErrorMessage.setText("Nessuna partita selezionata!");
    }

    /**
     * Go back to the main menù
     * @param actionEvent
     */
    public void goBack(ActionEvent actionEvent) {
       if (entered== false) {
           try {
               joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       else{
           try {
               ObserverGUI.Singleton().getServerController().leavethematch(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton());
           } catch (RemoteException e) {
               ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
           }
           try {
               joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    /**
     * Method for updating the list of available matches where the player can join
     * @param actionEvent
     */
    public void updateGameList(ActionEvent actionEvent) {
        gameList.getItems().removeAll();
        try {
            for (Match match : ObserverGUI.Singleton().getServerController().getActiveMatchesList()) {
                data.add(match.getName());
                gameList.setItems(data);
            }
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
    }

    /**
     * If the player selects a valid match to join its state is going to change to another in which the choice of the scheme
     * cards will be displayed
     * @param state
     */
    @Override
    public void update(State state){
        if(state== State.MUSTSETSCHEMECARD){
            Platform.runLater(()->{
                try {
                    joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/ChooseMap.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
