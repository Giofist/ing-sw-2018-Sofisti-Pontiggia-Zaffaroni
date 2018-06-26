package it.polimi.ingsw.ClientViewGUI;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.ClientView.Observer;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.Tile;
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

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.Color.BLACK;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.ansi;

public class ChooseMapController extends AbstractController implements Initializable {
    int mapSelected = 0;
    private int[] mapIDs = new int[4];
    private int index = 1;

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
        ErrorMessage.setStyle("-fx-text-fill:GREEN");
        ErrorMessage.setText("Hai selezionato la mappa:"+ mapSelected +". Tra poco si inizia!");
    }

    public void selectMap1(MouseEvent mouseEvent) {
        Select(mouseEvent);
        mapSelected = mapIDs[0];
        ErrorMessage.setText("Hai selezionato la mappa:"+ mapSelected +". Tra poco si inizia!");
    }

    public void selectMap2(MouseEvent mouseEvent) {
        Select(mouseEvent);
        mapSelected = mapIDs[1];
        ErrorMessage.setText("Hai selezionato la mappa:"+ mapSelected +". Tra poco si inizia!");
    }

    public void selectMap3(MouseEvent mouseEvent) {
        Select(mouseEvent);
        mapSelected = mapIDs[2];
        ErrorMessage.setText("Hai selezionato la mappa:"+ mapSelected +". Tra poco si inizia!");
    }

    public void selectMap4(MouseEvent mouseEvent) {
        Select(mouseEvent);
        mapSelected = mapIDs[3];
        ErrorMessage.setText("Hai selezionato la mappa:"+ mapSelected +". Tra poco si inizia!");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapIDs[0] = 13;
        try {
            for (SchemeCard schemeCard: ObserverGUI.Singleton().getServerController().getExtractedSchemeCard(ObserverGUI.Singleton().getUsername())){
                mapIDs[index-1] = schemeCard.getID();
            if(index==1) {setUpMap(schemeCard, gridMap1, mapName1, Diff11, Diff12, Diff13, Diff14, Diff15, Diff16);}
            else if (index==2){setUpMap(schemeCard, gridMap2, mapName2, Diff21, Diff22, Diff23, Diff24, Diff25, Diff26);}
            else if (index==3){setUpMap(schemeCard, gridMap3, mapName3, Diff31, Diff32, Diff33, Diff34, Diff35, Diff36);}
            else if (index==4){setUpMap(schemeCard, gridMap4, mapName4, Diff41, Diff42, Diff43, Diff44, Diff45, Diff46);}
                index++;
            }
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
     }
   }

    public void setUpMap(SchemeCard schemeCard, GridPane gridMap, Text mapName, Circle diff1, Circle diff2, Circle diff3, Circle diff4, Circle diff5, Circle diff6) {
        char[] charTile;
        char[] mapID;
        int row = 0;
        int column = 0;
        mapName.setText(schemeCard.getMapName());
        switch (schemeCard.getDifficulty()) {
            case 6:
                break;
            case 5:
                diff6.setVisible(false);
                break;
            case 4:
                diff6.setVisible(false);
                diff5.setVisible(false);
                break;
            case 3:
                diff6.setVisible(false);
                diff5.setVisible(false);
                diff4.setVisible(false);
                break;
            case 2:
                diff6.setVisible(false);
                diff5.setVisible(false);
                diff4.setVisible(false);
                diff3.setVisible(false);
                break;
            case 1:
                diff6.setVisible(false);
                diff5.setVisible(false);
                diff4.setVisible(false);
                diff3.setVisible(false);
                diff2.setVisible(false);
                break;
            default:
                break;
        }

        RowIterator rowIterator =  schemeCard.rowIterator(0);
        while(rowIterator.hasNext()){
            ColumnIterator columnIterator = schemeCard.columnIterator(rowIterator.getCurrentRow());
            while(columnIterator.hasNext()){
                Tile tile = columnIterator.next();
                if (tile.haveColor_constrain()){
                    switch (tile.getColor_Constrain()){
                        case YELLOW:
                            gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-color:YELLOW");
                            break;
                        case BLUE:
                            gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-color:BLUE");
                            break;
                        case RED:
                            gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-color:RED");
                            break;
                        case VIOLET:
                            gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-color:VIOLET");
                            break;
                        case GREEN:
                            gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-color:GREEN");
                            break;
                    }
                }
                else if (tile.haveNumber_constrain()){
                    gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-image: url('Dices/"+ tile.getNumber_Constrain() +".jpg'); -fx-background-position: center center;-fx-background-size: cover");

                }
                else{
                    gridMap.getChildren().get(rowIterator.getCurrentRow()*schemeCard.getMaxRow() + columnIterator.getCurrentColumn()).setStyle("-fx-background-color:WHITE");

                }

            }
            rowIterator.next();
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
