package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;

public class MultiCreateController {

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
        void createGame(ActionEvent event) {

        }

        @FXML
        void goBack(MouseEvent event) {

        }

    }

