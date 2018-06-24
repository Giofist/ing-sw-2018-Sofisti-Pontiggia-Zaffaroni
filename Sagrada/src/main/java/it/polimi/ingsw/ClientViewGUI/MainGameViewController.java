package it.polimi.ingsw.ClientViewGUI;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.tools.Tool;
import java.awt.event.MouseEvent;

public class MainGameViewController {
    ImageView origin = null;
    public Stage primaryStage; //only for testing

    @FXML
    private ImageView firstDice;

    @FXML
    private GridPane DicePool;

    @FXML
    private ImageView RoundDice0;

    @FXML
    private ImageView RoundDice1;

    @FXML
    private ImageView RoundDice2;

    @FXML
    private ImageView RoundDice3;

    @FXML
    private ImageView RoundDice4;

    @FXML
    private ImageView RoundDice5;

    @FXML
    private ImageView RoundDice6;

    @FXML
    private ImageView RoundDice7;

    @FXML
    private ImageView RoundDice8;

    @FXML
    private GridPane yourMap;

    @FXML
    private ImageView ImageView00;

    @FXML
    private ImageView ToolCard;

    @FXML
    private Text yourMapName;

    @FXML
    private Text yourMapDiff;

    @FXML
    void handleDragDetection(MouseEvent event) {

    }
    @FXML
    void handleImageDragOver(DragEvent event) {
        if(event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }


    @FXML
    void handleOnDragDropped(DragEvent event) {
        ImageView source = (ImageView) event.getTarget();
        source.fitHeightProperty();
        source.fitWidthProperty();
        source.setImage(event.getDragboard().getImage());
        origin.setImage(null);
    }

    public void handleDragDetection(javafx.scene.input.MouseEvent mouseEvent) {
        origin = (ImageView) mouseEvent.getTarget();
        Dragboard db = origin.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(origin.getImage());
        db.setContent(cb);
        mouseEvent.consume();
    }

    public void setPrimaryStage(Stage primaryStage) {  //to be removed only for gtesting
        this.primaryStage = primaryStage;
    }

    public void zoomCard(MouseDragEvent mouseDragEvent) {
        ToolCard.setScaleY(20);
        yourMapName.setText("OK");
    }

}
