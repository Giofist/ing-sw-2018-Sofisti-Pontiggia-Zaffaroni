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
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.ResourceBundle;

import static org.fusesource.jansi.Ansi.ansi;

public class ChooseMapController extends AbstractController implements Initializable {
    GridPane selected = null;
    String map;
    private Stage primaryStage;

    public ChooseMapController() {
        ObserverGUI.Singleton().setController(this);
    }

    @FXML
    private AnchorPane selectPane;

    @FXML
    private GridPane gridMap1;

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
    private GridPane gridMap2;

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
    private GridPane gridMap3;

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
    private GridPane gridMap4;

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
        selected = gridMap1;
        ErrorMessage.setText("1");
    }

    public void selectMap2(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap2;
        ErrorMessage.setText("2");
    }

    public void selectMap3(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap3;
        ErrorMessage.setText("3");
    }

    public void selectMap4(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap4;
        ErrorMessage.setText("4");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            ObserverGUI.Singleton().getServerController().getExtractedSchemeCard(ObserverGUI.Singleton().getUsername());
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }*/
        setUpMap(map, gridMap1);
        setUpMap(map, gridMap2);
        setUpMap(map, gridMap3);
        setUpMap(map, gridMap4);
    }

    public void setUpMap(String map, GridPane gridMap) {
        char[] charTile;
        int row = 0;
        int column = 0;
       /* String[] element = map.split("%");
        System.out.println(element[0]);
        System.out.println(element[1]);
        String[] tiles = element[2].split("!");
*/
        for (int j = 0; j < 4; j++) {
            {
                // for (String rowTile : tiles) {
                //String[] columnTiles = rowTile.split("-");
                //for (String el : columnTiles) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(j * 5 + i);
                    if(((j * 5 + i)%2) == 0) {
                        gridMap.getChildren().get(j * 5 + i).setStyle("-fx-background-color:RED");
                    }
                    else gridMap.getChildren().get(j * 5 + i).setStyle("-fx-background-image: url('Dices/4.jpg'); -fx-background-position: center center;-fx-background-size: cover");
                   // column++;
                }
               // row++;
                /*charTile = el.toCharArray();
                switch (charTile[1]) {
                    case 'Y':
                        gridMap.getChildren().get().setStyle();

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
        System.out.print("\n");*/

            }

     /*   public void setDifficulty(int difficulty) {
            switch (difficulty) {
                case 6:
                    break;
                case 5:
                    Diff6.setRadius(0);
                    break;
                case 4:
                    Diff6.setRadius(0);
                    Diff5.setRadius(0);
                    break;
                case 3:
                    Diff6.setRadius(0);
                    Diff5.setRadius(0);
                    Diff5.setRadius(0);
                    break;
                case 2:
                    Diff6.setRadius(0);
                    Diff5.setRadius(0);
                    Diff5.setRadius(0);
                    Diff4.setRadius(0);
                    break;
                case 1:
                    Diff6.setRadius(0);
                    Diff5.setRadius(0);
                    Diff5.setRadius(0);
                    Diff4.setRadius(0);
                    Diff2.setRadius(0);
                    break;
                default:
                    break;
            }
        }*/
        }
    }

        public void mapSelect (MouseEvent mouseEvent){
        }

        public void leaveTheMatch (javafx.event.ActionEvent actionEvent){
        }

    public void setPrimaryStage(Stage primaryStage) { 
        this.primaryStage = primaryStage;
    }
}
