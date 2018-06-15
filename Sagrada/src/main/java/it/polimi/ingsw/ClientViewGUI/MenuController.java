package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;


public class MenuController {

        @FXML
        private Text playerName;

        @FXML
        private Text PlayerScore;

        @FXML
        private ImageView PlayerImage;

        @FXML
        private JFXButton multiPlayer;

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
    }

    public void ExitGame(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setUpSinglePlayer(javafx.event.ActionEvent actionEvent) {
    }

    public void setUpMultiPlayer(javafx.event.ActionEvent actionEvent) {
    }
}