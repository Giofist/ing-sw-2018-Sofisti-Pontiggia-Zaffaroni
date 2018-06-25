package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Collections;

public class ChooseMapController {
    BorderPane selected = null;

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

    public void Select(javafx.scene.input.MouseEvent mouseEvent) {
        Pane map = (Pane) mouseEvent.getTarget();
        DropShadow dropShadow = new DropShadow();
        selected.setEffect(null);
        map.setEffect(dropShadow);
    }

    public void goBack(javafx.event.ActionEvent actionEvent) {
        ErrorMessage.setText("Voi Abbandonare la Partita?");
    }

    public void startGame(javafx.event.ActionEvent actionEvent) {
        ErrorMessage.setText("Iniziamo!");
        try {
            MenuController.getMainPane().getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MainGameView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectMap1(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = map1;
        ErrorMessage.setText("1");
    }

    public void selectMap2(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = map2;
        ErrorMessage.setText("2");
    }

    public void selectMap3(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = map3;
        ErrorMessage.setText("3");
    }

    public void selectMap4(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = map4;
        ErrorMessage.setText("4");
    }


}
