package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import static org.fusesource.jansi.Ansi.ansi;

public class ChooseMapController extends AbstractController implements Initializable {
    GridPane selected = null;
    int mapSelected = 0;
    private int[] mapIDs = new int[4];
    private int index = 0;

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

    public void Select(javafx.scene.input.MouseEvent mouseEvent) {
        BorderPane map = (BorderPane) mouseEvent.getTarget();
        DropShadow dropShadow = new DropShadow();
        selected.setEffect(null);
        map.setEffect(dropShadow);
        ErrorMessage.setStyle("-fx-text-fill:GREEN");
        ErrorMessage.setText("Hai selezionato la mappa:"+ mapSelected +". Tra poco si inizia!");
    }

    public void selectMap1(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap1;
        mapSelected = mapIDs[0];
    }

    public void selectMap2(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap2;
        mapSelected = mapIDs[1];
    }

    public void selectMap3(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap3;
        mapSelected = mapIDs[2];
    }

    public void selectMap4(MouseEvent mouseEvent) {
        Select(mouseEvent);
        selected = gridMap4;
        mapSelected = mapIDs[3];
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapIDs[0] = 13;
        try {
            for (SchemeCard schemeCard: ObserverGUI.Singleton().getServerController().getExtractedSchemeCard(ObserverGUI.Singleton().getUsername())){
            mapIDs[index] = schemeCard.getID();
            if(index+1==1) {setUpMap(ObserverGUI.Singleton().getTranslator().translateSchemeCard(schemeCard), gridMap1, mapName1, Diff11, Diff12, Diff13, Diff14, Diff15, Diff16);}
            else if (index+1==2){setUpMap(ObserverGUI.Singleton().getTranslator().translateSchemeCard(schemeCard), gridMap2, mapName2, Diff21, Diff22, Diff23, Diff24, Diff25, Diff26);}
            else if (index+1==3){setUpMap(ObserverGUI.Singleton().getTranslator().translateSchemeCard(schemeCard), gridMap3, mapName3, Diff31, Diff32, Diff33, Diff34, Diff35, Diff36);}
            else if (index+1==4){setUpMap(ObserverGUI.Singleton().getTranslator().translateSchemeCard(schemeCard), gridMap4, mapName4, Diff41, Diff42, Diff43, Diff44, Diff45, Diff46);}
                index++;
            }
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
     }
   }

    public void setUpMap(String map, GridPane gridMap, Text mapName, Circle diff1, Circle diff2, Circle diff3, Circle diff4, Circle diff5, Circle diff6) {
        char[] charTile;
        char[] mapID;
        int row = 0;
        int column = 0;
        String[] element = map.split("%");
        mapName.setText(element[0]);
        switch (element[1].toCharArray()[24]) {
            case '6':
                break;
            case '5':
                diff6.setVisible(false);
                break;
            case '4':
                diff6.setVisible(false);
                diff5.setVisible(false);
                break;
            case '3':
                diff6.setVisible(false);
                diff5.setVisible(false);
                diff4.setVisible(false);
                break;
            case '2':
                diff6.setVisible(false);
                diff5.setVisible(false);
                diff4.setVisible(false);
                diff3.setVisible(false);
                break;
            case '1':
                diff6.setVisible(false);
                diff5.setVisible(false);
                diff4.setVisible(false);
                diff3.setVisible(false);
                diff2.setVisible(false);
                break;
            default:
                break;
        }
        String[] tiles = element[2].split("!");

        for (String rowTile : tiles) {
            String[] columnTiles = rowTile.split("-");
            for (String el : columnTiles) {
                charTile = el.toCharArray();
                switch (charTile[1]) {
                    case 'Y':
                        gridMap.getChildren().get(row*5+column).setStyle("-fx-background-color:YELLOW");
                        break;
                    case 'B':
                        gridMap.getChildren().get(row*5+column).setStyle("-fx-background-color:BLUE");
                        break;
                    case 'R':
                        gridMap.getChildren().get(row*5+column).setStyle("-fx-background-color:RED");
                        break;
                    case 'V':
                        gridMap.getChildren().get(row*5+column).setStyle("-fx-background-color:VIOLET");
                        break;
                    case 'G':
                        gridMap.getChildren().get(row*5+column).setStyle("-fx-background-color:GREEN");
                        break;
                    case '*':
                        gridMap.getChildren().get(row * 5 + column).setStyle("-fx-background-image: url('Dices/"+ charTile[0] +".jpg'); -fx-background-position: center center;-fx-background-size: cover");
                        break;
                    case '_':
                        gridMap.getChildren().get(row*5+column).setStyle("-fx-background-color:WHITE");
                        break;

                    }
                column++;
            }
            row++;
        }
    }

    public void leaveTheMatch (javafx.event.ActionEvent actionEvent){
            try {
                ObserverGUI.Singleton().getServerController().leavethematch(ObserverGUI.Singleton().getUsername());
            } catch (RemoteException e) {
                ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
            }
        }

}
