package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.ResourceBundle;


public class MenuController extends AbstractController implements Initializable {
    Stage stage = null;
    Parent myNewScene = null;

    public MenuController(){
        ObserverGUI.Singleton().setController(this);
    }
        @FXML
        private static AnchorPane mainPane;

        @FXML
        private Text PlayerName;

        @FXML
        private Text PlayerScore;

        @FXML
        private ImageView PlayerImage;

        @FXML
        private JFXButton multiPlayer;

        @FXML
        private Text ErrorMessage;

        @FXML
        private JFXButton singlePlayer;

        @FXML
        private JFXButton settings;

        @FXML
        private AnchorPane content;

        @FXML
        private JFXButton Exit;

        @FXML
        void ExitGame(ActionEvent event) {
        }

        @FXML
        void setUpMultiPlayer(ActionEvent event) {

        }

        @FXML
        void setUpSinglePlayer(ActionEvent event) {

        }


        @FXML
        void settingsInterace(ActionEvent event) {

        }

    public void setUpMultiPlayer(javafx.event.ActionEvent actionEvent) {
        try {
            content.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void settingsInterace(javafx.event.ActionEvent actionEvent) {
        try {
            content.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/Settings.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExitGame(javafx.event.ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().logout(ObserverGUI.Singleton().getUsername());
        } catch (RemoteException e) {
            e.printStackTrace(); //lascio così
        }
        System.exit(0);
    }

    public void setUpSinglePlayer(javafx.event.ActionEvent actionEvent) {
        ErrorMessage.setText("Cooming Soon!");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlayerName.setText(ObserverGUI.Singleton().getUsername());
        PlayerScore.setText("");
    }

    public static AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }
}