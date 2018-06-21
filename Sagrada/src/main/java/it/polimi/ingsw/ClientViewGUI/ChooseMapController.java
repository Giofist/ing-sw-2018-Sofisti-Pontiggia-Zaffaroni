package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;

public class ChooseMapController {

    @FXML
    private AnchorPane selectPane;

    @FXML
    private Text ErrorMessage;

    @FXML
    private JFXButton Back;

    @FXML
    private JFXButton Play;

    @FXML
    private BorderPane map1;

    @FXML
    private GridPane yourMap;

    @FXML
    private Text yourMapName;

    @FXML
    private Text yourMapDiff;

    @FXML
    private BorderPane map2;

    @FXML
    private GridPane yourMap2;

    @FXML
    private Text yourMapName2;

    @FXML
    private Text yourMapDiff2;

    @FXML
    private BorderPane map3;

    @FXML
    private GridPane yourMap1;

    @FXML
    private Text yourMapName1;

    @FXML
    private Text yourMapDiff1;

    @FXML
    private BorderPane map4;

    @FXML
    private GridPane yourMap21;

    @FXML
    private Text yourMapName21;

    @FXML
    private Text yourMapDiff21;
    

    public void goBack(javafx.event.ActionEvent actionEvent) {
    }

    public void startGame(javafx.event.ActionEvent actionEvent) {
    }

    public void selectMap1(MouseEvent mouseEvent) {
        ErrorMessage.setText("1");
    }

    public void selectMap2(MouseEvent mouseEvent) {
        ErrorMessage.setText("2");
    }

    public void selectMap3(MouseEvent mouseEvent) {
        ErrorMessage.setText("3");
    }

    public void selectMap4(MouseEvent mouseEvent) {
        ErrorMessage.setText("4");
    }
}
