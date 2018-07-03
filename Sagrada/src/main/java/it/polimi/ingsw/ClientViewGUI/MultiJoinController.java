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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.ResourceBundle;

public class MultiJoinController  implements Initializable,AbstractController{
    private Boolean entered = false;
    ObservableList<String> data = FXCollections.observableArrayList();

    public MultiJoinController(){
        ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private AnchorPane joinPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private Button Back;

    @FXML
    private JFXListView<String> gameList;


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

    public void JoinGame(ActionEvent actionEvent) {
        String matchName = null;
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

    public void UpdateGameList(ActionEvent actionEvent) {
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
    @Override
    public void update(State state){
        if(state== State.MUSTSETSCHEMECARD){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    try {
                        joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/ChooseMap.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
