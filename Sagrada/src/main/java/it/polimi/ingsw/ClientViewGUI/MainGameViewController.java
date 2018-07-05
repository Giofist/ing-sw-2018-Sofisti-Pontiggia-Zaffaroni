package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.WrongToolCardIDException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;
import it.polimi.ingsw.model.SchemeDeck.Tile;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.model.ToolCard.ToolAction;
import it.polimi.ingsw.model.ToolCard.ToolCardsDeck;
import it.polimi.ingsw.model.ToolCard.ToolRequestClass;
import it.polimi.ingsw.model.TurnActions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

public class MainGameViewController implements Initializable, AbstractController {
    private ImageView origin = null;
    boolean selected = false;
    private String privateGoalcardPath = null;
    private int ToolCard1 = 0;
    private int ToolCard2 = 0;
    private int ToolCard3 = 0;
    private String promptAction = "";
    private int numOfPlayer = 0;
    private int selectedDiceInd = 10;
    private int DicesToMove = 0;
    private int newOldRow = 10;
    private int newOldColumn = 10;
    private int toolCardId = 0;
    private int numOfClick = 0;
    ToolRequestClass data = new ToolRequestClass();

    @FXML
    private ChoiceBox selectIntensity;

    @FXML
    private Button Select;

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private ToggleButton toggle1or2;

    @FXML
    private ToggleButton increaseDicIntensity;

    @FXML
    private Button select1or2;

    @FXML
    private Text Actions;

    @FXML
    private Text turnIndicator;

    @FXML
    private Button passTurnBn;

    @FXML
    private GridPane DicePool;

    @FXML
    private ImageView RoundDice0;

    @FXML
    private ImageView RoundDice1;

    @FXML
    private Button selectValue;

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
    private BorderPane P3Map;

    @FXML
    private BorderPane P2Map;

    @FXML
    private BorderPane P1Map;

    @FXML
    private Text ErrorMessage;

    @FXML
    private Text P1MapName;

    @FXML
    private Text Player1;

    @FXML
    private Circle P1Diff6;

    @FXML
    private Circle P1Diff5;

    @FXML
    private Circle P1Diff4;

    @FXML
    private Circle P1Diff3;

    @FXML
    private Circle P1Diff2;

    @FXML
    private Circle P1Diff1;

    @FXML
    private Text Player2;

    @FXML
    private Text P2MapName;

    @FXML
    private Circle P2Diff6;

    @FXML
    private Circle P2Diff5;

    @FXML
    private Circle P2Diff4;

    @FXML
    private Circle P2Diff3;

    @FXML
    private Circle P2Diff2;

    @FXML
    private Circle P2Diff1;

    @FXML
    private Text Player3;

    @FXML
    private GridPane roundTrack;

    @FXML
    private Text P3MapName;

    @FXML
    private Button useToolCard;

    @FXML
    private Text token;

    @FXML
    private Circle P3Diff1;

    @FXML
    private Circle P3Diff2;

    @FXML
    private Circle P3Diff3;

    @FXML
    private Circle P3Diff4;

    @FXML
    private Circle P3Diff5;

    @FXML
    private Circle P3Diff6;

    @FXML
    private ImageView PrivateGoalCard;

    @FXML
    private ImageView PublicGoalCard1;

    @FXML
    private ImageView PublicGoalCard2;

    @FXML
    private ImageView PublicGoalCard3;

    @FXML
    private ImageView ToolCardImage1;

    @FXML
    private ImageView ToolCardImage2;

    @FXML
    private ImageView ToolCardImage3;

    @FXML
    private  Text SelectedCell;

    @FXML
    private GridPane mapPlayer1;

    @FXML
    private GridPane mapPlayer2;

    @FXML
    private GridPane mapPlayer3;

    @FXML
    private Circle Diff6;

    @FXML
    private Circle Diff5;

    @FXML
    private Circle Diff3;

    @FXML
    private Circle Diff4;

    @FXML
    private Circle Diff1;

    @FXML
    private Circle Diff2;

    @FXML
    void handleImageDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleOnDragDropped(DragEvent event) {
        int row;
        int column;
        int index;

        ImageView destination = (ImageView) event.getTarget();
        if (yourMap.getRowIndex(destination.getParent()) == null) {
            row = 0;
        } else row = yourMap.getRowIndex(destination.getParent());

        if (yourMap.getColumnIndex(destination.getParent()) == null) {
            column = 0;
        } else column = yourMap.getColumnIndex(destination.getParent());

        if (DicePool.getColumnIndex(origin) == null) {
            index = 0;
        } else index = DicePool.getColumnIndex(origin);

        try {
            ObserverGUI.Singleton().getServerController().setDice(ObserverGUI.Singleton().getUsername(), index, row, column);
            destination.setImage(event.getDragboard().getImage());
            origin.setImage(null);
            ErrorMessage.setText("Hai piazzato il dado correttamente!");
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
    }

    public MainGameViewController() {
        ObserverGUI.Singleton().setController(this);
    }

    public void handleDragDetection(javafx.scene.input.MouseEvent mouseEvent) {
        char[] SelectedDiceId;

        ErrorMessage.setText(null);
        origin = (ImageView) mouseEvent.getTarget();
        Dragboard db = origin.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(origin.getImage());
        db.setContent(cb);
        SelectedDiceId = origin.getId().toCharArray();
        int selectedDiceIndex = Integer.parseInt(String.valueOf(SelectedDiceId[9]));
        mouseEvent.consume();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = null;
        toggle1or2.setVisible(false);
        select1or2.setVisible(false);
        selectIntensity.setVisible(false);
        useToolCard.setVisible(false);
        Select.setVisible(false);
        selectValue.setVisible(false);
        increaseDicIntensity.setVisible(false);
        updateToken();
        updateDicePool();
        updatePossiibleActions();

        try {
            updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCard(ObserverGUI.Singleton().getUsername()).get(0), yourMap);
        } catch (DiceNotExistantException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            numOfPlayer = ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).size();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            privateGoalcardPath = "PrivateGoalCards/" + ObserverGUI.Singleton().getServerController().getPrivateGoalCard(ObserverGUI.Singleton().getUsername()).get(0).getID() + ".jpg";
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            image = new Image("PublicGoalCards/" + ObserverGUI.Singleton().getServerController().getPublicGoalCards(ObserverGUI.Singleton().getUsername()).get(0).getID() + ".jpg");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        PublicGoalCard1.setImage(image);

        try {
            image = new Image("PublicGoalCards/" + ObserverGUI.Singleton().getServerController().getPublicGoalCards(ObserverGUI.Singleton().getUsername()).get(1).getID() + ".jpg");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        PublicGoalCard2.setImage(image);
        try {
            image = new Image("PublicGoalCards/" + ObserverGUI.Singleton().getServerController().getPublicGoalCards(ObserverGUI.Singleton().getUsername()).get(2).getID() + ".jpg");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        PublicGoalCard3.setImage(image);


        try {
            this.ToolCard1 = ObserverGUI.Singleton().getServerController().getToolCards(ObserverGUI.Singleton().getUsername()).get(0).getID();
            image = new Image("ToolCards/" + ToolCard1 + ".jpg");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ToolCardImage1.setImage(image);

        try {
            this.ToolCard2 = ObserverGUI.Singleton().getServerController().getToolCards(ObserverGUI.Singleton().getUsername()).get(1).getID();
            image = new Image("ToolCards/" + ToolCard2 + ".jpg");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ToolCardImage2.setImage(image);

        try {
            this.ToolCard3 = ObserverGUI.Singleton().getServerController().getToolCards(ObserverGUI.Singleton().getUsername()).get(2).getID();
            image = new Image("ToolCards/" + ToolCard3 + ".jpg");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        ToolCardImage3.setImage(image);

        try {
            setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCard(ObserverGUI.Singleton().getUsername()).get(0), yourMap, yourMapName, Diff1, Diff2, Diff3, Diff4, Diff5, Diff6);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setOtherPlayerMap();
        updateOtherPlayesMap();
    }

    public void highlight(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.1);
        source.setEffect(bloom);
    }

    public void remuveHighlight(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        source.setEffect(null);
    }

    public void showPrivateGoal(javafx.scene.input.MouseEvent mouseEvent) {
        Image image = new Image(privateGoalcardPath);
        PrivateGoalCard.setImage(image);
    }

    public void hidePrivateGoal(javafx.scene.input.MouseEvent mouseEvent) {
        Image image = new Image("PrivateGoalCards/Back1.jpg");  //todoremoveto be general
        PrivateGoalCard.setImage(image);
    }

    public void select(javafx.scene.input.MouseEvent mouseEvent) {
        ImageView source = (ImageView) mouseEvent.getTarget();
        DropShadow dropShadow = new DropShadow();
        source.setEffect(dropShadow);
    }

    public void unselect(javafx.scene.input.MouseEvent mouseEvent) {
        if (selected == false) {
            ImageView source = (ImageView) mouseEvent.getTarget();
            source.setEffect(null);
        }
    }

    public void setOtherPlayerMap() {
        Player pl;
        switch (numOfPlayer) {
            case 3:
                try {
                    setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(0), mapPlayer1, P1MapName, P1Diff1, P1Diff2, P1Diff3, P1Diff4, P1Diff5, P1Diff6);
                    setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(1), mapPlayer2, P2MapName, P2Diff1, P2Diff2, P2Diff3, P2Diff4, P2Diff5, P2Diff6);
                    setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(2), mapPlayer3, P3MapName, P3Diff1, P3Diff2, P3Diff3, P3Diff4, P3Diff5, P3Diff6);
                    pl = (Player) ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).get(0);
                    Player1.setText(pl.getName());
                    pl = (Player) ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).get(1);
                    Player2.setText(pl.getName());
                    pl = (Player) ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).get(2);
                    Player3.setText(pl.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(0), mapPlayer1, P1MapName, P1Diff1, P1Diff2, P1Diff3, P1Diff4, P1Diff5, P1Diff6);
                    setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(1), mapPlayer2, P2MapName, P2Diff1, P2Diff2, P2Diff3, P2Diff4, P2Diff5, P2Diff6);
                    pl = (Player) ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).get(0);
                    Player1.setText(pl.getName());
                    pl = (Player) ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).get(1);
                    Player2.setText(pl.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                P3Map.setVisible(false);
                Player3.setVisible(false);
                break;
            case 1:
                try {
                    setUpMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(0), mapPlayer2, P2MapName, P2Diff1, P2Diff2, P2Diff3, P2Diff4, P2Diff5, P2Diff6);
                    pl = (Player) ObserverGUI.Singleton().getServerController().getPlayersinmymatch(ObserverGUI.Singleton().getUsername()).get(0);
                    Player2.setText(pl.getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                P3Map.setVisible(false);
                Player3.setVisible(false);
                P1Map.setVisible(false);
                Player1.setVisible(false);
                break;
            default:
                break;
        }
    }

    public void updateOtherPlayesMap() {
        switch (numOfPlayer) {
            case 3:
                try {
                    updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(0), mapPlayer1);
                    updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(1), mapPlayer2);
                    updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(2), mapPlayer3);
                } catch (DiceNotExistantException e) {
                    ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                } catch (RemoteException e) {
                    ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                }
                break;
            case 2:
                try {
                    updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(0), mapPlayer1);
                    updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(1), mapPlayer2);
                } catch (RemoteException e) {
                    ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                } catch (DiceNotExistantException e) {
                    ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                }
                break;
            case 1:
                try {
                    updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCardsoftheotherPlayers(ObserverGUI.Singleton().getUsername()).get(0), mapPlayer2);
                } catch (RemoteException e) {
                    ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                } catch (DiceNotExistantException e) {
                    ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                }
                break;
            default:
                break;
        }
    }

    public void updateToken() {
        try {
            token.setText("Segnalini favore: " + String.valueOf(ObserverGUI.Singleton().getServerController().getToken(ObserverGUI.Singleton().getUsername())));
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
    }

    public void updateDiceInMap(SchemeCard schemeCard, GridPane gridMap) throws DiceNotExistantException {
        Pane cell = new Pane();
        Image pic;

        RowIterator rowIterator = schemeCard.rowIterator(0);
        while (rowIterator.hasNext()) {
            ColumnIterator columnIterator = schemeCard.columnIterator(rowIterator.getCurrentRow());
            while (columnIterator.hasNext()) {
                Tile tile = columnIterator.next();
                if (tile.isOccupied()) {
                    switch (tile.getDice().getColor()) {
                        case YELLOW: {
                            cell = (Pane) gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1);
                            pic = new Image("Dices/Y" + tile.getDice().getIntensity() + ".jpg");
                            ((ImageView) cell.getChildren().get(0)).setImage(pic);
                            break;
                        }
                        case BLUE: {
                            cell = (Pane) gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1);
                            pic = new Image("Dices/B" + tile.getDice().getIntensity() + ".jpg");
                            ((ImageView) cell.getChildren().get(0)).setImage(pic);
                            break;
                        }
                        case RED: {
                            cell = (Pane) gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1);
                            pic = new Image("Dices/R" + tile.getDice().getIntensity() + ".jpg");
                            ((ImageView) cell.getChildren().get(0)).setImage(pic);
                            break;
                        }
                        case VIOLET: {
                            cell = (Pane) gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1);
                            pic = new Image("Dices/V" + tile.getDice().getIntensity() + ".jpg");
                            ((ImageView) cell.getChildren().get(0)).setImage(pic);
                            break;
                        }
                        case GREEN: {
                            cell = (Pane) gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1);
                            pic = new Image("Dices/G" + tile.getDice().getIntensity() + ".jpg");
                            ((ImageView) cell.getChildren().get(0)).setImage(pic);
                            break;
                        }
                    }

                }else {
                    cell = (Pane) gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1);
                    ((ImageView) cell.getChildren().get(0)).setImage(null);
                }
            }
            rowIterator.next();
        }
    }

    public void updateDicePool() {
        int i = 0;
        char[] charDice;
        String[] dices = new String[0];
        String name;
        ImageView image = null;
        String imagePath;


        try {
            dices = ObserverGUI.Singleton().getServerController().getRoundDicepool(ObserverGUI.Singleton().getUsername()).split("-");
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        //Image emptyPic = new Image("Dices/EmptySpace.jpg");  //TODO migliorabile!!!
        RoundDice0.setImage(null);
        RoundDice1.setImage(null);
        RoundDice2.setImage(null);
        RoundDice3.setImage(null);
        RoundDice4.setImage(null);
        RoundDice5.setImage(null);
        RoundDice6.setImage(null);
        RoundDice7.setImage(null);
        RoundDice8.setImage(null);

        if (!dices[0].equals("")) {
            for (String dice : dices) {
                charDice = dice.toCharArray();
                imagePath = "Dices/" + charDice[1] + charDice[0] + ".jpg";

                switch (i) {
                    case 0: {
                        Image pic = new Image(imagePath);
                        RoundDice0.setImage(pic);
                        break;
                    }
                    case 1: {
                        Image pic = new Image(imagePath);
                        RoundDice1.setImage(pic);
                        break;
                    }
                    case 2: {
                        Image pic = new Image(imagePath);
                        RoundDice2.setImage(pic);
                        break;
                    }
                    case 3: {
                        Image pic = new Image(imagePath);
                        RoundDice3.setImage(pic);
                        break;
                    }
                    case 4: {
                        Image pic = new Image(imagePath);
                        RoundDice4.setImage(pic);
                        break;
                    }
                    case 5: {
                        Image pic = new Image(imagePath);
                        RoundDice5.setImage(pic);
                        break;
                    }
                    case 6: {
                        Image pic = new Image(imagePath);
                        RoundDice6.setImage(pic);
                        break;
                    }
                    case 7: {
                        Image pic = new Image(imagePath);
                        RoundDice7.setImage(pic);
                        break;
                    }
                    case 8: {
                        Image pic = new Image(imagePath);
                        RoundDice8.setImage(pic);
                        break;
                    }
                }
                i++;
            }
        }
    }

    public void updateRoundTrack() {
        String track;
        int round = 0;
        int row = 0;
        String[] dicesColumn = new String[10];
        String[] dice = new String[5];
        String imagePath;
        try {
            dicesColumn = ObserverGUI.Singleton().getServerController().getRoundTrack(ObserverGUI.Singleton().getUsername()).split("!");
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        for (String dices : dicesColumn) {
            row = 0;
            dice = dices.split("-");
            for (String elem : dice) {
                ImageView Imm = new ImageView();
                Imm = (ImageView) roundTrack.getChildren().get(row * 10 + round);
                imagePath = "Dices/" + elem.toCharArray()[1] + elem.toCharArray()[0] + ".jpg";
                Image pic = new Image(imagePath);
                Imm.setImage(pic);
                row++;
            }
            round++;
        }
    }

    public void selectDice(javafx.scene.input.MouseEvent mouseEvent) {
        selectedDiceInd = Integer.parseInt(String.valueOf(((ImageView) mouseEvent.getTarget()).getId().toCharArray()[9]));
        SelectedCell.setText("Dadi della Round Dicepool: "+ selectedDiceInd);
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

        RowIterator rowIterator = schemeCard.rowIterator(0);
        while (rowIterator.hasNext()) {
            ColumnIterator columnIterator = schemeCard.columnIterator(rowIterator.getCurrentRow());
            while (columnIterator.hasNext()) {
                Tile tile = columnIterator.next();
                if (tile.haveColor_constrain()) {
                    switch (tile.getColor_Constrain()) {
                        case YELLOW:
                            gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-color:YELLOW");
                            break;
                        case BLUE:
                            gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-color:BLUE");
                            break;
                        case RED:
                            gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-color:RED");
                            break;
                        case VIOLET:
                            gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-color:VIOLET");
                            break;
                        case GREEN:
                            gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-color:GREEN");
                            break;
                    }
                } else if (tile.haveNumber_constrain()) {
                    gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-image: url('Dices/" + tile.getNumber_Constrain() + ".jpg'); -fx-background-position: center center;-fx-background-size: cover");
                } else {
                    gridMap.getChildren().get(rowIterator.getCurrentRow() * schemeCard.getMaxColumn() + columnIterator.getCurrentColumn() - 1).setStyle("-fx-background-color:WHITE");

                }
            }
            rowIterator.next();
        }
    }

    public void leaveTheMatch(javafx.event.ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().leavethematch(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton());
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        try {
            backgroundPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePossiibleActions() {
        promptAction = "";
        try {
            for (TurnActions action : ObserverGUI.Singleton().getServerController().getPossibleActions(ObserverGUI.Singleton().getUsername())) {
                promptAction += " -";
                promptAction += ObserverGUI.Singleton().getTranslator().translateTurnAction(action);
                promptAction += "\n";
            }
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        Actions.setText(promptAction);
    }

    public void UseToolcard(MouseEvent mouseEvent) {
        ImageView card = (ImageView) mouseEvent.getTarget();
        String input = "0";
        Boolean correct = false;
        Boolean success = false;
        Boolean condition = false;
        int token = 0;
        int cost = 0;

        switch (((ImageView) mouseEvent.getTarget()).getId()) {
            case "ToolCardImage1": {
                toolCardId = ToolCard1;
                break;
            }
            case "ToolCardImage2": {
                toolCardId = ToolCard2;
                break;
            }
            case "ToolCardImage3": {
                toolCardId = ToolCard3;
                break;
            }
        }
        try {
            token = ObserverGUI.Singleton().getServerController().getToken(ObserverGUI.Singleton().getUsername());
            for (ToolAction toolAction: ObserverGUI.Singleton().getServerController().getToolCards(ObserverGUI.Singleton().getUsername())) {
                if (toolAction.getID() == toolCardId) {
                    cost = toolAction.getCost();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(token >= cost) {
            numOfClick = 0;
            data.setToolCardID(toolCardId);
            SelectedCell.setText("Hai selezionato la carta: " + toolCardId);
            if (selected == false) {
                DropShadow dropShadow = new DropShadow();
                card.setEffect(dropShadow);
                switch (toolCardId) {
                    case 1: { //1. Pinze Sgrossatrice
                        ErrorMessage.setText("Clicca sul dado della DicePool e decidi se aumentare o ridurre l'intensità!");
                        Select.setVisible(true);
                        increaseDicIntensity.setVisible(true);
                        break;
                    }
                    case 2: { //2. Pennello per Eglomise
                        ErrorMessage.setText("Seleziona il dado da spostare (Ignora colore):");
                        Select.setVisible(true);
                        break;
                    }
                    case 3: { //3. Alesatore per lamina di rame
                        ErrorMessage.setText("Seleziona il dado da spostare (Ignora intensità):");
                        Select.setVisible(true);
                        break;
                    }
                    case 4: { //4. Lathekin
                        ErrorMessage.setText("Seleziona il primo dado da spostare.");
                        Select.setVisible(true);
                        break;
                    }
                    case 5: { //5. Taglierina circolare
                        ErrorMessage.setText("Seleziona un dado sulla DicePool e uno sulla RoundTrack con cui scambiarlo.");
                        Select.setVisible(true);
                        break;
                    }
                    case 6: { //6. Pennello per Pasta Salda
                        ErrorMessage.setText("Seleziona il dado da tirare nuovamente. Indica l'indice:");
                        Select.setVisible(true);
                        break;
                    }
                    case 7: { //7. Martelletto
                        useToolCard.setVisible(true);
                        break;
                    }
                    case 8: { //8. Tenaglia a Rotelle
                        ErrorMessage.setText("Piazza subito un secondo dado. Seleziona il dado:");
                        Select.setVisible(true);
                        break;
                    }
                    case 9: { //9. Riga in Sughero
                        ErrorMessage.setText("Seleziona il dado da posizionare nella mappa.");
                        Select.setVisible(true);
                        break;
                    }
                    case 10: { //10. Tampone Diamantato
                        ErrorMessage.setText("Seleziona il dado da girare sulla faccia opposta.");
                        Select.setVisible(true);
                        break;
                    }
                    case 11: { //11. Diluente per Pasta Salda
                        ErrorMessage.setText("Seleziona il dado rimettere nel sacchetto.");
                        Select.setVisible(true);
                        break;
                    }
                    case 12: { //12. Taglierina Manuale
                        ErrorMessage.setText("Seleziona il numero di dadi da spostare.");
                        toggle1or2.setVisible(true);
                        select1or2.setVisible(true);
                        break;
                    }
                }
            } else {
                ErrorMessage.setText("Non puoi usare due Carte Utensile contemporaneamente!");
            }
        }
        else{
            ErrorMessage.setText("Non hai abbastanza Segnalini favore!");
        }
    }

    public void useTool(ActionEvent actionEvent) throws RemoteException {
        useToolCard.setVisible(false);
        SelectedCell.setText("");
        try {
            ObserverGUI.Singleton().getServerController().useaToolCard(ObserverGUI.Singleton().getUsername(), data);
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        updateDicePool();
        updateRoundTrack();
        updateOtherPlayesMap();
        try {
            updateDiceInMap(ObserverGUI.Singleton().getServerController().getSchemeCard(ObserverGUI.Singleton().getUsername()).get(0), yourMap);
        } catch (DiceNotExistantException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        selected = false;
    }

    public void passTurn(ActionEvent actionEvent) {
        try {
            ObserverGUI.Singleton().getServerController().passTurn(ObserverGUI.Singleton().getUsername());
            ErrorMessage.setText("Hai passato il turno!");
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
    }

    @Override
    public void update(State state) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateToken();
                updateRoundTrack();
                updateDicePool();
                updatePossiibleActions();
                updateOtherPlayesMap();
                ErrorMessage.setText("");
            }
        });

        if (state == State.STARTTURNSTATE) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    passTurnBn.setVisible(true);
                    turnIndicator.setText("E' il tuo turno!");
                }
            });
        } else if (state == State.NOTYOURTURNSTATE) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    turnIndicator.setText("Non è il tuo turno.");
                    passTurnBn.setVisible(false);
                    try {
                        ObserverGUI.Singleton().getServerController().passTurn(ObserverGUI.Singleton().getUsername());
                    } catch (RemoteException e) {
                        ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                    }
                }
            });
        } else if (state == State.ENDMATCHSTATE) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        backgroundPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/Result.fxml"))));
                    } catch (IOException e) {
                        ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                    }
                }
            });
        } else if (state == State.FORCEENDMATCH) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    turnIndicator.setText("Hai vinto a tavolino!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        ObserverGUI.Singleton().getServerController().leavethematch(ObserverGUI.Singleton().getUsername(), ObserverGUI.Singleton());
                    } catch (RemoteException e) {
                        ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                    }
                    try {
                        backgroundPane.getChildren().setAll(Collections.singleton(FXMLLoader.load(getClass().getResource("/MenuPartial.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (state == State.MUSTPASSTURNSTATE) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObserverGUI.Singleton().getServerController().passTurn(ObserverGUI.Singleton().getUsername());
                    } catch (RemoteException e) {
                        ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
                    }
                }
            });
        } else if (state == State.MUSTSSETDILUENTEPERPASTASALDASTATE) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateDicePool();
                    selectIntensity.setVisible(true);
                    selectIntensity.getItems().addAll("1", "2", "3", "4", "5", "6"); //modifico qui con stesso criterio
                    newOldColumn = 10;
                    newOldRow = 10;
                    ErrorMessage.setText("Seleziona dove vuoi mettere il dado estratto (se non vuoi piazzlo non cliccare nulla) e l'intensità.");
                    selectValue.setVisible(true);
                }
            });

        } else if (state == State.MUSTSETPENNELLOPERPASTASALDASTATE) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateDicePool();
                    ErrorMessage.setText("Seleziona dove vuoi mettere il dado estratto (se non vuoi piazzlo non cliccare nulla):");
                    newOldColumn = 10;
                    newOldRow = 10;
                    selectValue.setVisible(true);
                }
            });
        }else if (state == State.ERRORSTATE){
            ErrorMessage.setText("C'è stato un errore nella creazione della partita");
        }
    }

    public void Select1or2(ActionEvent actionEvent) {
        if (toggle1or2.isSelected()) {
            DicesToMove = 2;
        } else DicesToMove = 1;
        data.setNumberofDicesyouwanttomove(DicesToMove);
        SelectedCell.setText("Dadi da spostare: "+ DicesToMove);
        toggle1or2.setVisible(false);
        select1or2.setVisible(false);
        Select.setVisible(true);
        ErrorMessage.setText("Seleziona il dado da spostare.");
    }

    public void selectThisPose(MouseEvent mouseEvent) {
        newOldRow = ((ImageView) mouseEvent.getTarget()).getId().toCharArray()[9]-48;
        newOldColumn = ((ImageView) mouseEvent.getTarget()).getId().toCharArray()[10]-48;
        SelectedCell.setText("Hai selezionato la cella: " + newOldRow + " "+ newOldColumn);
    }

    public void selectThisPoseSpecial(MouseEvent mouseEvent) {
        newOldRow = ((ImageView) mouseEvent.getTarget()).getId().toCharArray()[10]-48;
        newOldColumn = ((ImageView) mouseEvent.getTarget()).getId().toCharArray()[11]-48;
        SelectedCell.setText("Hai selezionato la cella: "+ newOldRow + " "+ newOldColumn);
    }

    public void toolcardSecondStep(ActionEvent actionEvent) {
        switch (toolCardId) {
            case 1: {
                data.setRoundWhereThediceis(selectedDiceInd);
                if (increaseDicIntensity.isSelected()) {
                    data.setOperationforPinzaSgrossatrice(0);
                }else data.setOperationforPinzaSgrossatrice(1);
                Select.setVisible(false);
                increaseDicIntensity.setVisible(false);
                useToolCard.setVisible(true);
                break;
            }
            case 6: case 10: case 11:{
                data.setRoundWhereThediceis(selectedDiceInd);
                Select.setVisible(false);
                useToolCard.setVisible(true);
                break;
            }
            case 2:
            case 3: {
                if (numOfClick == 0) {
                    data.setOldRow1(newOldRow);
                    data.setOldColumn1(newOldColumn);
                    ErrorMessage.setText("Seleziona la nuova posizione del dado.");
                    numOfClick++;
                } else {
                    data.setNewRow1(newOldRow);
                    data.setNewColumn1(newOldColumn);
                    Select.setVisible(false);
                    useToolCard.setVisible(true);
                }
                break;
            }
            case 4: {
                if (numOfClick == 0) {
                    data.setOldRow1(newOldRow);
                    data.setOldColumn1(newOldColumn);
                    ErrorMessage.setText("Seleziona la nuova posizione del dado.");
                    numOfClick++;
                } else if (numOfClick == 1) {
                    data.setNewRow1(newOldRow);
                    data.setNewColumn1(newOldColumn);
                    ErrorMessage.setText("Seleziona il secondo dado da spostare.");
                    numOfClick++;
                } else if (numOfClick == 2) {
                    data.setOldRow2(newOldRow);
                    data.setOldColumn2(newOldColumn);
                    ErrorMessage.setText("Seleziona la nuova posizione del dado.");
                    numOfClick++;
                } else {
                    data.setNewRow2(newOldRow);
                    data.setNewColumn2(newOldColumn);
                    Select.setVisible(false);
                    useToolCard.setVisible(true);
                }
                break;
            }
            case 5:{
                data.setSelectedDiceIndex(selectedDiceInd);
                data.setRoundWhereThediceis(newOldColumn + 1);
                data.setSelectedRoundTrackDiceIndex(newOldRow);
                Select.setVisible(false);
                useToolCard.setVisible(true);
                break;
            }
            case 8:{
                if(numOfClick==0) {
                    data.setSelectedDiceIndex(selectedDiceInd);
                    ErrorMessage.setText("Seleziona la posizione sulla mappa:");
                    numOfClick++;
                }
                else {
                    data.setNewRow1(newOldRow);
                    data.setNewColumn1(newOldColumn);
                    Select.setVisible(false);
                    useToolCard.setVisible(true);
                }
                break;
            }
            case 9:{
                if(numOfClick==0) {
                    data.setSelectedDiceIndex(selectedDiceInd); //solo per avere mesaggi diversi
                    ErrorMessage.setText("Seleziona la cella isolata in cui piazzarlo.");
                    numOfClick++;
                }
                else {
                    data.setNewRow1(newOldRow);
                    data.setNewColumn1(newOldColumn);
                    Select.setVisible(false);
                    useToolCard.setVisible(true);
                }
                break;
            }
            case 12:{
                if (DicesToMove ==1) {
                    if (numOfClick == 0) {
                        data.setOldRow1(newOldRow);
                        data.setOldColumn1(newOldColumn);
                        ErrorMessage.setText("Seleziona la nuova posizione del dado.");
                        numOfClick++;
                    } else {
                        data.setNewRow1(newOldRow);
                        data.setNewColumn1(newOldColumn);
                        Select.setVisible(false);
                        useToolCard.setVisible(true);
                    }
                }else {
                    if (numOfClick == 0) {
                        data.setOldRow1(newOldRow);
                        data.setOldColumn1(newOldColumn);
                        ErrorMessage.setText("Seleziona la nuova posizione del dado.");
                        numOfClick++;
                    } else if (numOfClick == 1) {
                        data.setNewRow1(newOldRow);
                        data.setNewColumn1(newOldColumn);
                        ErrorMessage.setText("Seleziona il secondo dado da spostare.");
                        numOfClick++;
                    } else if (numOfClick == 2) {
                        data.setOldRow2(newOldRow);
                        data.setOldColumn2(newOldColumn);
                        ErrorMessage.setText("Seleziona la nuova posizione del dado.");
                        numOfClick++;
                    } else {
                        data.setNewRow2(newOldRow);
                        data.setNewColumn2(newOldColumn);
                        Select.setVisible(false);
                        useToolCard.setVisible(true);
                        }
                }
                break;
            }
            }
        }

    public void selectValueOfDice(ActionEvent actionEvent) {
        selectValue.setVisible(false);
        selectIntensity.setVisible(false);
        SelectedCell.setText("Intensità scelta:"+ selectIntensity.getValue());
        try{
            ObserverGUI.Singleton().getServerController().setToolCardDiceIntensity(ObserverGUI.Singleton().getUsername(),Integer.parseInt(selectIntensity.getValue().toString()));
        } catch (RemoteException e) {
            ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
        }
        try{
        ObserverGUI.Singleton().getServerController().setToolCardDice(ObserverGUI.Singleton().getUsername(), newOldRow, newOldColumn);
    } catch (RemoteException e) {
        ErrorMessage.setText(ObserverGUI.Singleton().getTranslator().translateException(e.getMessage()));
    }
    }

    public void increaseDecrease(ActionEvent actionEvent) {
        if (increaseDicIntensity.isSelected()){
            SelectedCell.setText("Diminuisci l'intensità del dado di 1.");
        }
        else SelectedCell.setText("Aumeta l'intensità del dado di 1.");
    }

    public void move1or2(ActionEvent actionEvent) {
        if (toggle1or2.isSelected()){
            SelectedCell.setText("Sposta 2 dadi!");
        }
        else SelectedCell.setText("Sposta un solo dado!");
    }
}
