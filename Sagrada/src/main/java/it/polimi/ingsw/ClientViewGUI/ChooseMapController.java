package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.ResourceBundle;

public class ChooseMapController extends AbstractController implements Initializable {
    GridPane selected = null;

    public ChooseMapController(){
        ObserverGUI.Singleton().setController(this);
    }

        @FXML
        private AnchorPane selectPane;

        @FXML
        private GridPane map1;

        @FXML
        private Text mapName1;

        @FXML
        private Text yourMapDiff;

        @FXML
        private Circle Diff16;

        @FXML
        private Circle Diff15;

        @FXML
        private Circle Diff13;

        @FXML
        private Circle Diff14;

        @FXML
        private Circle Diff11;

        @FXML
        private Circle Diff12;

        @FXML
        private Text mapName2;

        @FXML
        private Text yourMapDiff2;

        @FXML
        private Circle Diff26;

        @FXML
        private Circle Diff25;

        @FXML
        private Circle Diff23;

        @FXML
        private Circle Diff24;

        @FXML
        private Circle Diff21;

        @FXML
        private Circle Diff22;

        @FXML
        private GridPane map2;

        @FXML
        private Text mapName3;

        @FXML
        private Text yourMapDiff1;

        @FXML
        private Circle Diff36;

        @FXML
        private Circle Diff35;

        @FXML
        private Circle Diff33;

        @FXML
        private Circle Diff34;

        @FXML
        private Circle Diff31;

        @FXML
        private Circle Diff32;

        @FXML
        private GridPane map3;

        @FXML
        private Text mapName4;

        @FXML
        private Text yourMapDiff3;

        @FXML
        private Circle Diff46;

        @FXML
        private Circle Diff45;

        @FXML
        private Circle Diff43;

        @FXML
        private Circle Diff44;

        @FXML
        private Circle Diff41;

        @FXML
        private Circle Diff42;

        @FXML
        private GridPane map4;

        @FXML
        private JFXButton Back;

        @FXML
        private Text ErrorMessage;

        @FXML
        private JFXButton Play;

        @FXML
        void goBack(ActionEvent event) {

        }

        @FXML
        void startGame(ActionEvent event) {

        }

    public void Select(javafx.scene.input.MouseEvent mouseEvent) {
        Pane map = (Pane) mouseEvent.getTarget();
        DropShadow dropShadow = new DropShadow();
        selected.setEffect(null);
        map.setEffect(dropShadow);
    }

    public void goBack(javafx.event.ActionEvent actionEvent) {
        try {
            selectPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObserverGUI.Singleton().getServerController().getExtractedSchemeCard(ObserverGUI.Singleton().getUsername());
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }


    }

    /*public void setUpMap(String map){
        char[] charTile;
        String[] element = map.split("%");
        System.out.println(element[0]);
        System.out.println(element[1]);
        String[] tiles = element[2].split("!");

        for (String rowTile : tiles) {
            String[] column = rowTile.split("-");
            for (String el : column) {
                charTile = el.toCharArray();
                switch (charTile[1]) {
                    case 'Y':

                        break;
                    case 'B':

                        break;
                    case 'R':

                        break;
                    case 'V':
                        break;
                    case 'G':
                        System.out.print(ansi().eraseScreen().bg(GREEN).a("   ").reset());
                        break;
                    case '*':
                        System.out.print(ansi().eraseScreen().bg(WHITE).fg(BLACK).a(" " + charTile[0] + " ").reset()); //constrain intensity
                        break;
                    case '_':
                        System.out.print(ansi().eraseScreen().bg(WHITE).fg(BLACK).a("   ").reset());  //empty
                        break;

            }
            System.out.print("\n");
        }
        System.out.print("\n");

    }*/
}
