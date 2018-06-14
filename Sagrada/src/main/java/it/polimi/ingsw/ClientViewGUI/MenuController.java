package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class MenuController {


    private Stage menuStage;

    public class PleaseProvideControllerClassName {

        @FXML
        private Text playerName;

        @FXML
        private Text totalPlayerScore;

        @FXML
        private ImageView playerImage;

        @FXML
        private JFXButton multiPlayer;

        @FXML
        private JFXButton singlePlayer;

        @FXML
        private JFXButton settings;

    }
    public void setmenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

}
