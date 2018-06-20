package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MultiJoinGameController {
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
    private JFXListView<?> gameList;

    @FXML
    private JFXButton joinaGame;

    public void SelectGame(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void JoinGame(javafx.event.ActionEvent actionEvent) {
    }

    public void goBack(javafx.scene.input.MouseEvent mouseEvent) {
        stage = (Stage) backButton.getScene().getWindow();
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
}
