package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ResultController {
    Stage stage = null;
    Parent myNewScene = null;

    @FXML
    private Text WinnerMessage;

    @FXML
    private Text First;

    @FXML
    private Text Second;

    @FXML
    private Text Third;

    @FXML
    private Text Forth;

    @FXML
    private JFXButton Playagain;

    @FXML
    private JFXButton BackToMenu;

    public void PlayAgain(javafx.event.ActionEvent actionEvent) {
        stage = (Stage) Playagain.getScene().getWindow();
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Multi");
        stage.show();

    }

    public void BackToMenu(javafx.event.ActionEvent actionEvent) {
        stage = (Stage) BackToMenu.getScene().getWindow();
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
}
