package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

public class MultiCreateController {

        @FXML
        private AnchorPane createPane;

        @FXML
        private Text ErrorMessage;

        @FXML
        private JFXTextField gameName;

        @FXML
        private JFXButton createGame;

        @FXML
        private JFXButton Back;


        public void createGame(ActionEvent actionEvent) {
                ErrorMessage.setText("Non ancora implementato!");
        }

        public void goBack(ActionEvent actionEvent) {
                try {
                        createPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
