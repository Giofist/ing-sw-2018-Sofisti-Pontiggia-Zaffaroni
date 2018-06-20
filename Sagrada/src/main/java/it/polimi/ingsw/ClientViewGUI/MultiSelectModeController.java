package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class MultiSelectModeController {
    Stage stage = null;
    Parent myNewScene = null;

        @FXML
        private Text backButton;

        @FXML
        private Text yourName;

        @FXML
        private Text yourScore;

        @FXML
        private ImageView yourImage;

        @FXML
        private JFXButton createGame;

        @FXML
        private JFXButton joinGame;

        @FXML
        void goBack(MouseEvent event) {
            stage = (Stage) backButton.getScene().getWindow();
            try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Multi");
            stage.show();

        }

        public void CreateGame(javafx.event.ActionEvent actionEvent) {
            stage = (Stage) createGame.getScene().getWindow();
            try {
                myNewScene = FXMLLoader.load(getClass().getResource("/MultiCreateGame.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Create");
            stage.show();

        }

    public void JoinGame(javafx.event.ActionEvent actionEvent) {
        stage = (Stage) joinGame.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("/MultiJoinGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Multi");
        stage.show();
        }
}


