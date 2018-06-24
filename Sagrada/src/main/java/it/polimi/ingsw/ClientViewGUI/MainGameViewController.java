package it.polimi.ingsw.ClientViewGUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.tools.Tool;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MainGameViewController implements Initializable{
    ImageView origin = null;
    public Stage primaryStage; //only for testing
    int SelectedDiceIndex;

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
    private ImageView ToolCard;

    @FXML
    private Text yourMapName;

    @FXML
    private ImageView ImageView00;

    @FXML
    private ImageView ImageView01;

    @FXML
    private ImageView ImageView02;

    @FXML
    private ImageView ImageView03;

    @FXML
    private ImageView ImageView04;

    @FXML
    private ImageView ImageView10;

    @FXML
    private ImageView ImageView12;

    @FXML
    private ImageView ImageView13;

    @FXML
    private ImageView ImageView14;

    @FXML
    private ImageView ImageView20;

    @FXML
    private ImageView ImageView21;

    @FXML
    private ImageView ImageView24;

    @FXML
    private ImageView ImageView30;

    @FXML
    private ImageView ImageView31;

    @FXML
    private ImageView ImageView32;

    @FXML
    private ImageView ImageView34;

    @FXML
    private ImageView ImageView11;

    @FXML
    private ImageView ImageView22;

    @FXML
    private ImageView ImageView23;

    @FXML
    private ImageView ImageView33;

    @FXML
    private ImageView PrivateGoalCard;

    @FXML
    private ImageView PublicGoalCard1;

    @FXML
    private ImageView PublicGoalCard2;

    @FXML
    private ImageView PublicGoalCard3;


    @FXML
    private ProgressBar TimeLine;

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
        Image image = new Image("Dices/EmptySpace.jpg");
        origin.setImage(image);
    }

    public void handleDragDetection(javafx.scene.input.MouseEvent mouseEvent) {
        char[] SelectedDiceId = new char[11];
        origin = (ImageView) mouseEvent.getTarget();
        Dragboard db = origin.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(origin.getImage());
        db.setContent(cb);
        SelectedDiceId = origin.getId().toCharArray();
        SelectedDiceIndex = Integer.parseInt(String.valueOf(SelectedDiceId[9]));  //TODO magari sistemare semplificando Per ora va
        mouseEvent.consume();
    }


    public void setPrimaryStage(Stage primaryStage) {  //to be removed only for gtesting
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String mapName = "Celestial";
        yourMapName.setText(mapName);
    }

    public void Highlight(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.1);
        source.setEffect(bloom);
    }

    public void RemuveHighlight(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        source.setEffect(null);
    }

    public void ShowPrivateGoal(javafx.scene.input.MouseEvent mouseEvent) {
        Image image = new Image("GoalCards/SfumatureVerdi.jpg");  //todo remove to be general non voglio riscaricare tutte le volte riferimento
        PrivateGoalCard.setImage(image);

    }

    public void HidePrivateGoal(javafx.scene.input.MouseEvent mouseEvent) {
        Image image = new Image("GoalCards/Back1.jpg");  //todoremoveto be general
        PrivateGoalCard.setImage(image);
    }

    public void Select(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        DropShadow dropShadow = new DropShadow();
        source.setEffect(dropShadow);
    }

    public void Unselect(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        source.setEffect(null);
    }
}
