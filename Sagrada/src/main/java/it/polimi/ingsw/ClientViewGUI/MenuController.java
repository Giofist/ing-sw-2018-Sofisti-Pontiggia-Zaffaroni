package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class MenuController {
    Stage stage = null;
    Parent myNewScene = null;

        @FXML
        private Text playerName;

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

    public void settingsInterace(javafx.event.ActionEvent actionEvent) {
        stage = (Stage) settings.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("/Impostazioni.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExitGame(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setUpSinglePlayer(javafx.event.ActionEvent actionEvent) {
        ErrorMessage.setText("Cooming Soon!");
    }

    public void setUpMultiPlayer(javafx.event.ActionEvent actionEvent) {
        stage = (Stage) multiPlayer.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("/Multi create game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}