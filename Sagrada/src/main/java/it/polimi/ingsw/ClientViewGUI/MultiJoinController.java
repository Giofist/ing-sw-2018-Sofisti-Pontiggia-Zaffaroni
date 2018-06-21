package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

public class MultiJoinController {

    @FXML
    private AnchorPane joinPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXListView<?> gameList;

    @FXML
    private JFXButton joinaGame;

    @FXML
    private JFXButton Back;


    public void SelectGame(MouseEvent mouseEvent) {
    }

    public void JoinGame(ActionEvent actionEvent) {
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            joinPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MultiSelectMode.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
