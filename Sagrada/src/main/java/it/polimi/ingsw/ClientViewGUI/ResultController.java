package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;
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

public class ResultController implements Initializable,AbstractController {
    String playerList = "";
    int i = 1;

    @FXML
    private AnchorPane winnerList;

    @FXML
    private Text ErrorMessage;

    @FXML
    private Text listWinner;

    public ResultController(){
        ObserverGUI.Singleton().setController(this);
    }

    /**
     * Situation in which the player at the end of the match decides to play again
     * @param actionEvent Click on the button
     */
    public void playAgain(ActionEvent actionEvent) {
        try {
            winnerList.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Situation in which the player wants to go back to the main menù after terminating a match
     * @param actionEvent Click on the button
     */
    public void backToMenu(ActionEvent actionEvent) {
        try {
            winnerList.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays on screen the final score board of the match
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                for(Player player: ObserverGUI.Singleton().getServerController().getRanking(ObserverGUI.Singleton().getUsername())){
                    playerList += i + ". ";
                    playerList += player.getName();
                    playerList += "         ";
                    playerList += player.getPoints();
                    playerList += "\n";
                    i++;
                }
            } catch (RemoteException e) {
                ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        listWinner.setText(playerList);
    }

    /**
     * In this situation we don't receive any update because the match is terminated
     * @param state
     */
    @Override
    public void update(State state) {
        //in ENDMATCHSTATE we don't receive any update
    }
}
