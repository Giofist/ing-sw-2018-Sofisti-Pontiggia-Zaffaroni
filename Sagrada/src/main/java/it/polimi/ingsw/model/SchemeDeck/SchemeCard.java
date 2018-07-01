package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static it.polimi.ingsw.model.DiceColor.*;


public class SchemeCard implements Iterable<Tile>, Serializable{
    private int difficulty=0;
    private Tile[][] matrix;
    private SchemeCard twinCard;
    private int ID;
    private String MapName;
    private int maxRow;
    private int maxColumn;


    /**
     * This method loads a specific scheme card from a .txt file
     * @param mapID The id corresponding to the map I want to load
     * @throws IOException Exception thrown in case we encounter some problems when trying to read the constrains from the file
     * @throws MapConstrainReadingException Exception thrown if we encounter some problem in interpreting the scheme card's constrains
     */
    public SchemeCard(int mapID) throws IOException, MapConstrainReadingException {
        this.ID = mapID;
        String fileName = "src/main/resources/Maps.txt";
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {

            // Here we scan the file until we find the line corresponding to the map that we are interested to load
            for (int j = 0; j < (mapID-1) * 5 + 1; j++) {
                buffer.readLine();
            }

            //set the map name
            this.MapName = buffer.readLine();

            //setDifficulty
            this.difficulty = Integer.parseInt(buffer.readLine());

            //setRow
            this.maxRow = Integer.parseInt(buffer.readLine());

            //setColumn
            this.maxColumn = Integer.parseInt(buffer.readLine());

            matrix = new Tile[maxRow][maxColumn];

            char[] map = buffer.readLine().toCharArray();

            //set Tiles
            for(int row=0; row<maxRow; row++) {
                for (int column = 0; column < maxColumn; column++) {
                    this.matrix[row][column] = new Tile(row,column);
                    Tile tile = this.matrix[row][column];
                    switch (map[row * 5 + column]) {
                        case 'Y':
                            tile.setColourConstrain(DiceColor.YELLOW);
                            tile.setHaveColor_constrain(true);
                            break;
                        case 'B':
                            tile.setColourConstrain(DiceColor.BLUE);
                            tile.setHaveColor_constrain(true);
                            break;
                        case 'R':
                            tile.setColourConstrain(RED);
                            tile.setHaveColor_constrain(true);
                            break;
                        case 'V':
                            tile.setColourConstrain(VIOLET);
                            tile.setHaveColor_constrain(true);
                            break;
                        case 'G':
                            tile.setColourConstrain(GREEN);
                            tile.setHaveColor_constrain(true);
                            break;
                        case '1':
                            tile.setNumberConstrain(1);
                            tile.setHaveNumber_constrain(true);
                            break;
                        case '2':
                            tile.setNumberConstrain(2);
                            tile.setHaveNumber_constrain(true);
                            break;
                        case '3':
                            tile.setNumberConstrain(3);
                            tile.setHaveNumber_constrain(true);
                            break;
                        case '4':
                            tile.setNumberConstrain(4);
                            tile.setHaveNumber_constrain(true);
                            break;
                        case '5':
                            tile.setNumberConstrain(5);
                            tile.setHaveNumber_constrain(true);
                            break;
                        case '6':
                            tile.setNumberConstrain(6);
                            tile.setHaveNumber_constrain(true);
                            break;
                        case '_':
                            break;
                        default:
                            throw new MapConstrainReadingException();
                    }
                }
            }

        }
        catch (Exception e){
            throw e;
        }
    }


    /**
     * Each scheme card has two faces with a different board, this method allows you to set the twin cards
     * @param schemeCard Scheme card we want as twin
     */
    public void setTwinCard(SchemeCard schemeCard) {
        this.twinCard = schemeCard;
    }


    // Getter methods

    /**
     * @return The difficulty associated to the scheme card
     */
    public int getDifficulty() {
        return this.difficulty;
    }


    /**
     * @return The unique id associated to the scheme card
     */
    public int getID(){ return this.ID; }


    /**
     * @return The official name of the scheme card
     */
    public String getMapName(){ return this.MapName; }


    /**
     * @return How many rows the scheme card has
     */
    public int getMaxRow(){return this.maxRow;}


    /**
     * @return How many columns the scheme card has
     */
    public int getMaxColumn(){return this.maxColumn;}


    /**
     * This method allows to place a given dice in a specific tile. With specific parameters we can also control which level
     * of constrain we want when placing the dice.
     * @param dice The dice we want to place
     * @param row In which row we want to place the dice
     * @param column In which column we want to place the dice
     * @param IgnoreColor Boolean value whether we want to consider or not the color constrain
     * @param IgnoreNumber Boolean value whether we want to consider or not the intensity constrain
     * @param IgnoreThereisaDiceNearYou Boolean value whether we want to consider or not the constrain in placing the dice next to another one
     * @throws OutOfMatrixException Exception thrown when we try to place a dice in a position out of the scheme card
     * @throws TileConstrainException Exception thrown when some constrain isn't respected
     */
    public void setDice(Dice dice, int row, int column, boolean IgnoreColor, boolean IgnoreNumber, boolean IgnoreThereisaDiceNearYou)throws OutOfMatrixException, TileConstrainException {
        if(IsTileOccupied(row,column)){
            throw new TileyetOccupiedException(); // you can't set a dice where there is another dice
        }

        //to control the color and intensity constrain of the matrix
        for(int i = row-1; i<= row+1; i++){
            for(int j = column-1; j<= column+1; j++){
                try{
                    if(i==row  || j == column){
                        if (this.getDiceColour(i,j) == dice.getColor() && IgnoreColor == false){
                            throw new DiceSameColorNearYouException();
                        }
                        if(this.getDiceIntensity(i,j)== dice.getIntensity() && IgnoreNumber == false){
                            throw new DiceSameIntensityNearYou();
                        }
                    }
                }catch (OutOfMatrixException e){
                    //
                }catch (DiceNotExistantException er){
                    //
                }
            }
        }

        // to control if there is a dice near the tile where a want to set my dice
        boolean ThereisaDicenearYou = false;
        ThereisaDicenearYou = this.ThereisaDicenearYou(row,column) || IgnoreThereisaDiceNearYou;

        //if this is the first dice you set, there is a specific constrain
        if (EmptyScheme()){
            if(row ==0 || row == getMaxRow()-1 || column ==0 || column == getMaxColumn()-1){
                this.getTile(row,column).setDice(dice, IgnoreColor, IgnoreNumber);
            }else throw new FirstDiceNeedsToBeAtBordersException();
        }
        else{
            if(ThereisaDicenearYou){
                this.getTile(row,column).setDice(dice, IgnoreColor, IgnoreNumber);
            } else throw new NotNearAnotherDiceException();  // there must be a dice near you mate!
        }
    }


    /**
     * Method particularly useful for "Pennello per pasta Salda" tool card. It returns true if we can set the dice in the specified position
     * @param dice The dice we would like to place
     * @param row In which row we want to place the dice
     * @param column In which column we want to place the dice
     * @param IgnoreColor Boolean value whether we want to consider or not the color constrain
     * @param IgnoreNumber Boolean value whether we want to consider or not the intensity constrain
     * @return
     */
    public boolean SettableHere(Dice dice, int row, int column, boolean IgnoreColor, boolean IgnoreNumber) {
        try {
            if (IsTileOccupied(row, column)) {
                return false; // you can't set a dice where there is another dice
            }
            List colorsnearyou = new LinkedList<DiceColor>();
            List intensitiesnearyou = new LinkedList<DiceColor>();

            // to control if there is a dice near the tile where a want to set my dice

            //if this is the first dce you set, there is a specific constrain
            if (EmptyScheme()) {
                if (row ==0 || row == getMaxRow()-1 || column ==0 || column == getMaxColumn()-1) {
                    return this.getTile(row, column).settableDiceHere(dice, IgnoreColor, IgnoreNumber);
                } else return false;
            } else {
                if (ThereisaDicenearYou(row, column)) {
                    if (colorsnearyou.contains(dice.getColor())) {
                        return false;
                    }
                    if (intensitiesnearyou.contains(dice.getIntensity())) {
                        return false;
                    }
                    return this.getTile(row, column).settableDiceHere(dice, IgnoreColor, IgnoreNumber);
                } else return false;  // there must be a dice near you mate!
            }
        }catch(Exception e){
            return false;
        }
    }


    /**
     * Returns the color of the dice in the specified position
     * @param row Row where the dice is placed
     * @param column Column where the dice is placed
     * @return The color of the dice
     * @throws OutOfMatrixException Exception thrown in case we specify a position out from the scheme card
     * @throws DiceNotExistantException Exception thrown in case we specify a position with no dice in it
     */
    public  DiceColor getDiceColour (int row, int column)throws OutOfMatrixException, DiceNotExistantException{
        return this.getTile(row, column).getDice().getColor();
    }


    /**
     * Returns the intensity of the dice in the specified position
     * @param row Row where the dice is placed
     * @param column Column where the dice is placed
     * @return The intensity of the dice
     * @throws OutOfMatrixException Exception thrown in case we specify a position out from the scheme card
     * @throws DiceNotExistantException Exception thrown in case we specify a position with no dice in it
     */
    public int getDiceIntensity(int row, int column)throws OutOfMatrixException, DiceNotExistantException{
        return this.getTile(row, column).getDice().getIntensity();
    }


    /**
     * This method controls if the position on the scheme card is already occupied by another dice
     * @param row Row where we want to check
     * @param column Column where we want to check
     * @return True if the tile il already occupied
     * @throws OutOfMatrixException Exception thrown in case we specify a position out from the scheme card
     */
    public boolean IsTileOccupied(int row, int column) throws OutOfMatrixException{
        return this.getTile(row,column).isOccupied();
    }


    /**
     * @return The twin card associated
     */
    public SchemeCard getTwinCard() {
        return this.twinCard;
    }


    /**
     * @param row Row from where to get the dice
     * @param column Column from where to get the dice
     * @return The dice in the specified position
     * @throws DiceNotExistantException Exception thrown in case we specify a position with no dice in it
     * @throws OutOfMatrixException Exception thrown in case we specify a position out from the scheme card
     */
    public Dice getDice(int row, int column)throws DiceNotExistantException,OutOfMatrixException{
        return this.getTile(row,column).getDice();
    }


    /**
     * @param row Row from where to remove the dice
     * @param column Column from where to remove the dice
     * @throws DiceNotExistantException Exception thrown in case we specify a position with no dice in it
     * @throws OutOfMatrixException Exception thrown in case we specify a position out from the scheme card
     */
    public void removeDice(int row, int column) throws DiceNotExistantException, OutOfMatrixException{
        this.getTile(row,column).removeDice();
    }


    /**
     * This method checks if in the 8 tiles around one we specify there is at least one dice.
     * @param row Row where we want to check
     * @param column Column where we want to check
     * @return True if we find at least one dice in one of the 8 tiles around that one that we specify
     */
    public boolean ThereisaDicenearYou(int row, int column){
        boolean ThereisaDicenearYou = false;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                try {
                    ThereisaDicenearYou = ThereisaDicenearYou || IsTileOccupied(i, j);
                } catch (OutOfMatrixException e) {
                    //
                }
            }
        }
        return ThereisaDicenearYou;
    }


    /**
     * @param row Row from where to get the tile of the scheme card
     * @param column Column from where to get the tile of the scheme card
     * @return The tile in the position we specify
     * @throws OutOfMatrixException Exception thrown in case we specify a position out from the scheme card
     */
    public Tile getTile(int row, int column)throws OutOfMatrixException{
        if(row <0 || row > getMaxRow()-1 || column <0 || column > getMaxColumn()-1){
            throw new OutOfMatrixException();
        }
        return this.matrix[row][column];
    }
     private boolean EmptyScheme(){
        boolean empty = true;
        for(Tile tile: this){
            empty = empty && !tile.isOccupied();
        }
        return empty;
     }


    /**
     * This method returns an iterator over all the tiles of a scheme card
     * @return A scheme card iterator
     */
    @Override
    public Iterator<Tile> iterator() {
        return new Iterator<Tile>() {
            private int currentRow=0, currentColumn=0;
            private boolean deadEnd = false;

            @Override
            public boolean hasNext() {
                return !deadEnd;
            }

            @Override
            public void remove() {
                // To be implemented
            }

            @Override
            public Tile next() {
                Tile nextElement;
                try {
                    nextElement = getTile(currentRow, currentColumn);
                } catch (OutOfMatrixException e) {
                    throw new NoSuchElementException("Matrix dead end reached.");
                }
                currentColumn++;
                if (currentColumn == getMaxColumn()) {
                    currentColumn = 0;
                    currentRow++;
                    if (currentRow == getMaxRow()) {
                        deadEnd = true;
                    }
                }
                return nextElement;
            }
        };
    }


    /**
     * This method returns an iterator over a specific row
     * @param row The row we want to iterate on
     * @return A column iterator
     */
    public ColumnIterator<Tile> columnIterator(int row){
        return new ColumnIterator<Tile>(this,row);
    }


    /**
     * This method returns an iterator over a specific column
     * @param column The column we want to iterate on
     * @return A row iterator
     */
    public RowIterator<Tile> rowIterator(final int column){
        return new RowIterator<Tile>(this,column);
    }

}

