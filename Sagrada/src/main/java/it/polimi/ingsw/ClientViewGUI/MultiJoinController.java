package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXListView;
import it.polimi.ingsw.model.Match;
import it.polimi.ingsw.model.PlayerPackage.State;
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

public class MultiJoinController extends AbstractController implements Initializable{

    ObservableList<String> data = FXCollections.observableArrayList();

    public MultiJoinController(){
        ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private AnchorPane joinPane;

    @FXML
    private Text ErrorMessage;

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
        try {
            ObserverGUI.Singleton().getServerController().joinaMatch(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton(), gameList.getSelectionModel().getSelectedItem());
            } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        try {
            joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/ChooseMap.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateGameList(ActionEvent actionEvent) {
        gameList.setItems(null);
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

        if(state == State.MATCHNOTSTARTEDYETSTATE){
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    try {
                        joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/WaitInterface.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


}
