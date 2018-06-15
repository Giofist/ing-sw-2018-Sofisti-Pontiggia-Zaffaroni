package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MultiCreateController {
        Stage stage = null;
        Parent myNewScene = null;

        @FXML
        private Text backButton;

        @FXML
        private VBox yourName;

        @FXML
        private Text yourScore;

        @FXML
        private ImageView yourImage;

        @FXML
        private JFXTextField gameName;

        @FXML
        private JFXButton createGame;

        @FXML
        private  Text errorMessage;

        @FXML
        void createGame(ActionEvent event) {

        }

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
                //stage.setMaximized(true);
                stage.setTitle("Multi");
                stage.show();
        }

        public void createGame(javafx.event.ActionEvent actionEvent) {
                errorMessage.setText("Non ancora implementata!");
        }
}

