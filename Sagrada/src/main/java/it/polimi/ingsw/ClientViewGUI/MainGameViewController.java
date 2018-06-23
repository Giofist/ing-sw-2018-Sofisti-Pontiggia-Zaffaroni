package it.polimi.ingsw.ClientViewGUI;

import javafx.fxml.FXML;
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
    public Stage primaryStage; //only for testing

    @FXML
    private ImageView firstDice;

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
        ImageView00.fitHeightProperty();
        ImageView00.fitWidthProperty();
        ImageView00.setImage(event.getDragboard().getImage());
    }

    public void handleDragDetection(javafx.scene.input.MouseEvent mouseEvent) {
        Dragboard db = firstDice.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(firstDice.getImage());
        db.setContent(cb);
        mouseEvent.consume();
        firstDice.setImage(null);
    }

    public void setPrimaryStage(Stage primaryStage) {  //to be removed only for gtesting
        this.primaryStage = primaryStage;
    }

    public void zoomCard(MouseDragEvent mouseDragEvent) {
        ToolCard.setScaleY(20);
        yourMapName.setText("OK");
    }
}
