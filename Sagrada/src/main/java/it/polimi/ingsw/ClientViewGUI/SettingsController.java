package it.polimi.ingsw.ClientViewGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    Stage stage = null;
    Parent myNewScene = null;

    @FXML
    private Text backButton;

    @FXML
    private AnchorPane content;

    @FXML
    private Text yourName;

    @FXML
    private Text yourScore;

    @FXML
    private ImageView yourImage;

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

}


