package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.model.State;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

public class WaitInterfaceController extends AbstractController{

    public WaitInterfaceController(){
        ObserverGUI.Singleton().setController(this);
    }
    @FXML
    private AnchorPane createPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXButton LeaveTheGame;


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
            Platform.runLater(new Runnable(){
                @Override
                public void run(){

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
                }
            });
        }
        else if(state == State.FORCEENDMATCH){
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
