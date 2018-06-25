package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.model.PlayerPackage.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collections;

public class WaitInterfaceController extends AbstractController{

    @FXML
    private AnchorPane createPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXButton LeaveTheGame;


    public void leaveTheGame(javafx.event.ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().leavethematch(ObserverGUI.Singleton().getUsername());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/Menu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(State state){
        if(state!= State.MATCHNOTSTARTEDYETSTATE){
            try {
                createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/ChooseMap.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
