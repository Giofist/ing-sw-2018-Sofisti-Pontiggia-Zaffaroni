package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.model.Player;
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
import java.util.List;
import java.util.ResourceBundle;

public class ResultController extends AbstractController implements Initializable{
    String playerList = "";
    int i = 1;

    public ResultController(){
        ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private AnchorPane winnerList;

    @FXML
    private Text ErrorMessage;

    @FXML
    private Text WinnerMessage;

    @FXML
    private Text listWinner;


    public void PlayAgain(ActionEvent actionEvent) {
        try {
            winnerList.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BackToMenu(ActionEvent actionEvent) {
        try {
            winnerList.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                for(Object pl : ObserverGUI.Singleton().getServerController().getRanking(ObserverGUI.Singleton().getUsername())){
                    playerList += i + ". ";
                    playerList += ((Player) pl).getName();
                    playerList += "         ";
                    playerList += ((Player) pl).getPoints();
                    playerList += "\n";
                    i++;
                }
            } catch (RemoteException e) {
                ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        listWinner.setText(playerList);
    }
}
