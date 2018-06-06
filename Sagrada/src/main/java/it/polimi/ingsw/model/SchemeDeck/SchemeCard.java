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

//classe completa

public class SchemeCard implements Iterable<Tile>, Serializable{
    private int difficulty=0;
    private Tile[][] matrix;
    private SchemeCard twinCard;
    private int ID;
    private String MapName;
    private String mapString;
    char[] map;
    int maxRow;
    int maxColumn;

    //constructor
    public SchemeCard(int mapID) throws IOException, MapConstrainReadingException {
        this.ID = mapID;
        String fileName = "src/main/resources/Maps.txt";
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {

            //SCANDISCO IL FILE FINO ALLA RIGA Dove si trova la mappa che devo caricare
            for (int j = 0; j < (mapID-1) * 5 + 1; j++) {
                buffer.readLine();
            }

            //set the map name
            this.MapName = buffer.readLine();
            System.out.println("Titolo della mappa: " + this.getMapName());

            //setDifficulty
            this.difficulty = Integer.parseInt(buffer.readLine());
            System.out.println("Difficoltà: " + this.getDifficulty());

            //setRow
            this.maxRow = Integer.parseInt(buffer.readLine());

            //setColumn
            this.maxColumn = Integer.parseInt(buffer.readLine());

            matrix = new Tile[maxRow][maxColumn];

            this.mapString = buffer.readLine();
            this.map = mapString.toCharArray();
            System.out.println(map);

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
                            throw new MapConstrainReadingException(this.getID());
                    }
                }
            }

            System.out.println("--------------");
        }
        catch (Exception e){
            throw e;
        }
    }

    public void setTwinCard(SchemeCard schemeCard) {
        System.out.println("This has been the Twin Map\n\n");
        this.twinCard = schemeCard;
    }


    //get methods
    public int getDifficulty() {
        return this.difficulty;
    }

    public int getID(){ return this.ID; }

    public String getMapName(){ return this.MapName; }
    public int getMaxRow(){return this.maxRow;}
    public int getMaxColumn(){return this.maxColumn;}
    public String getMapString(){ return (this.maxRow + "-" + this.maxColumn + "-" + this.mapString);}



    //to set a Dice, this method is a bit long just because off the big number of controls I need to do here
    public void setDice(Dice dice, int row, int column, boolean IgnoreColor, boolean IgnoreNumber, boolean IgnoreThereisaDiceNearYou)throws OutOfMatrixException, TileConstrainException {
        if(IsTileOccupied(row,column)){
            throw new TileyetOccupiedException(); // you can't set a dice where there is another dice
        }

        //to control the color and intensity constrain of the matrix
        for(int i = row-1; i<= row+1; i++){
            for(int j = column-1; j<= column+1; j++){
                try{
                    if(i==row  || j == column){
                        if (this.getDiceColour(i,j) == dice.getColor()){
                            throw new DiceSameColorNearYouException();
                        }
                        if(this.getDiceIntensity(i,j)== dice.getIntensity()){
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
    //utilizzato in pennello per pasta salda
    public boolean SettableHere(Dice dice, int row, int column, boolean IgnoreColor, boolean IgnoreNumber) {
        try {
            if (IsTileOccupied(row, column)) {
                return false; // you can't set a dice where there is another dice
            }
            List colorsnearyou = new LinkedList<DiceColor>();
            List intensitiesnearyou = new LinkedList<DiceColor>();
            boolean ThereisaDicenearYou = false;

            // to control if there is a dice near the tile where a want to set my dice

            //if this is the first dce you set, there is a specific constrain
            if (EmptyScheme()) {
                if (row ==0 || row == getMaxRow()-1 || column ==0 || column == getMaxColumn()-1) {
                    return this.getTile(row, column).settableDiceHere(dice, IgnoreColor, IgnoreNumber);
                } else return false;
            } else {
                if (ThereisaDicenearYou) {
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


    public  DiceColor getDiceColour (int row, int column)throws OutOfMatrixException, DiceNotExistantException{
        return this.getTile(row, column).getDice().getColor();
    }
    public int getDiceIntensity(int row, int column)throws OutOfMatrixException, DiceNotExistantException{
        return this.getTile(row, column).getDice().getIntensity();
    }
    public boolean IsTileOccupied(int row, int column) throws OutOfMatrixException{
        return this.getTile(row,column).isOccupied();
    }


    //può essere utile questa classe?
    public boolean HaveFullColumn(int column) throws OutOfMatrixException{
        boolean havefullColumn = true;
        for(int i=0 ; i<getMaxRow() ; i++){
            havefullColumn = havefullColumn&&this.getTile(i,column).isOccupied();
        }
        return havefullColumn;
    }
    public boolean HaveFullRow(int row) throws OutOfMatrixException{
        boolean havefullRow = true;
        int i=0;
        for(i=0 ; i<getMaxColumn() ; i++){
            havefullRow = havefullRow&&this.getTile(row,i).isOccupied();
        }
        return havefullRow;
    }
    public SchemeCard getTwinCard() {
        return this.twinCard;
    }

    public Dice getDice(int row, int column)throws DiceNotExistantException,OutOfMatrixException{
        return this.getTile(row,column).getDice();

    }
    public void removeDice(int row, int column) throws DiceNotExistantException, OutOfMatrixException{
        this.getTile(row,column).removeDice();
    }
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

    public char[] getMap(){
        return this.map;
    }

    public String displayScheme(){
        String  string = this.getMapName() + "-" + getDifficulty()+ "-" + getMaxRow() + "-" + getMaxColumn() + "-" + this.mapString;
        return string;
    }


    //metodi private
    // lo setto private per non esporre l'implementazione
    public Tile getTile(int row, int column)throws OutOfMatrixException{
        if(row <0 || row > getMaxRow()-1 || column <0 || column > getMaxColumn()-1){
            throw new OutOfMatrixException();
        }
        return this.matrix[row][column];
    }
     private boolean EmptyScheme(){
        boolean empty = true;
        try{
            for (int row=0; row < getMaxRow(); row ++){
                for(int column = 0; column < getMaxColumn(); column ++){
                    empty = empty && !IsTileOccupied(row,column);
                }
            }
        }catch (OutOfMatrixException e){
            //impossibile cadere qui, questo continuo dover gestire una cosa che gestisco già con i cicli for mi fa pensare a fare un iteratore!
            //alla fine un iteratore è un ciclo for miglirato e poco altro, ma dà parechci vantaggi
            // la soluzione finale sarebbe iterare con java funzionale, ma questo, beh, non sono ancora in grado di farlo
            // già che c'ero, l'ho messo qua sotto copiato pari pari da affo
            //boh, magari un giorno lo useremo
            // vi consiglio di studiare pagina 671 del manuale di java, a me è piaciuta molto guys
        }
        return empty;
     }



    //per le classi anonime pag. 333
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
    //questi iteratori mi servono per gli obiettivi
    public ColumnIterator<Tile> columnIterator(int row){
        return new ColumnIterator<Tile>(this,row);
    }
    public RowIterator<Tile> rowIterator(final int column){
        return new RowIterator<Tile>(this,column);
    }

    @Override
    public String toString(){
        String schemeCardstring = "";
        schemeCardstring += getMapName();
        schemeCardstring += "%Difficoltà della mappa: ";
        schemeCardstring += getDifficulty();
        schemeCardstring += "%";
        for (int row = 0; row < maxRow; row++) {
            for (int column = 0 ; column < maxColumn; column++) {
                if (this.matrix[row][column].isOccupied()){
                    try {
                        schemeCardstring += this.matrix[row][column].getDice().getIntensity();
                        schemeCardstring += this.matrix[row][column].getDice().getColor().toString().toLowerCase();
                    } catch (DiceNotExistantException e) {
                        e.printStackTrace();
                    }
                }
                else if (this.matrix[row][column].haveColor_constrain()){
                    schemeCardstring += "0";
                    schemeCardstring += this.matrix[row][column].getColor_Constrain().toString();
                }
                else if (this.matrix[row][column].haveNumber_constrain()){
                    schemeCardstring += this.matrix[row][column].getNumber_Constrain();
                    schemeCardstring += "*";
                }
                else{
                    schemeCardstring += "0_";
                }
                schemeCardstring += "-";
                }
            schemeCardstring += "!";
            }
        return schemeCardstring;
    }
}

