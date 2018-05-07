package it.polimi.ingsw.model.SchemeDeck;
import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.FirstDiceNeedsToBeAtBordersException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.NotNearAnotherDiceException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileyetOccupiedException;

import java.io.*;
import java.lang.String;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static it.polimi.ingsw.model.DiceColor.GREEN;
import static it.polimi.ingsw.model.DiceColor.VIOLET;

//classe completa

public class SchemeCard implements Iterable<Tile>{
    private int difficulty;
    private Tile[][] matrix = new Tile[4][5];
    private SchemeCard twinCard;
    private int ID;
    private String MapName;

    //constructor
    public SchemeCard(int mapID) throws IOException, MapConstrainReadingException {
        this.initialize( mapID);
    }

    //to initialize the schemecard
    public void initialize (int mapID ) throws IOException,MapConstrainReadingException {
        String fileName = "Maps.txt";
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {

            //SCANDISCO IL FILE FINO ALLA RIGA Dove si trova la mappa che devo caricare
            for (int j = 0; j < mapID * 3 + 1; j++) {
                buffer.readLine();
            }

            //set the map name
            this.MapName = buffer.readLine();

            //setDifficulty
            this.difficulty = Integer.parseInt(buffer.readLine());

            //VARIABILE DI SUPPORTO
            char[] map = new char[20];
            map = buffer.readLine().toCharArray();


            //set Tiles
            for(int row=0; row<4; row++) {
                for (int b = 0; b < 5; b++) {
                    Tile tile = this.matrix[row][b];
                    switch (map[row * 5 + b]) {
                        case 'Y':
                            tile.setColourConstrain(DiceColor.YELLOW);
                            tile.setHaveColor_constrain(true);
                            break;
                        case 'B':
                            tile.setColourConstrain(DiceColor.BLUE);
                            tile.setHaveColor_constrain(true);
                            break;
                        case 'P':
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
                        default:
                            throw new MapConstrainReadingException();
                    }
                }
            }
        }
        catch (Exception e){
            throw e;
        }
        this.twinCard = new SchemeCard(mapID+1);
    }


    //get methods
    public int getDifficulty() {
        return this.difficulty;
    };
    public int getID(){ return this.ID; };
    public String getMapName(){ return this.MapName; }


    //to set a Dice, this method is a bit long just because off the big number of controls I need to do here
    public void setDice (Dice dice, int row, int column, boolean IgnoreColor, boolean IgnoreNumber)throws OutOfMatrixException, TileConstrainException {
        boolean ThereisaDicenearYou = false;

        // to control if there is a dice near the tile where a want to set my dice
        ThereisaDicenearYou = IsTileOccupied(row -1, column -1) || IsTileOccupied(row -1, column )||IsTileOccupied(row -1, column +1) ||
            IsTileOccupied(row , column -1) || IsTileOccupied(row -1, column +1) || IsTileOccupied(row +1, column -1)
                || IsTileOccupied(row +1, column ) || IsTileOccupied(row +1, column+1 ) ;

        //if this is the first dce you set, there is a specific constrain
        if (EmptyScheme()){
            if(row ==0 || row == 3 || column ==0 || column ==4){
                this.getTile(row,column).setDice(dice, IgnoreColor, IgnoreNumber);
            }else throw new FirstDiceNeedsToBeAtBordersException();
        }
        else{
            if (IsTileOccupied(row,column)){
                throw new TileyetOccupiedException(); // you can't set a dice where there is another dice
            }
            else if(ThereisaDicenearYou){
                this.getTile(row,column).setDice(dice, IgnoreColor, IgnoreNumber);
            } else throw new NotNearAnotherDiceException();  // there must be a dice near you mate!
        }

        //others controls need tobe implemented? check out please!
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
        for( int i=0; i<4;i++){
            havefullColumn = havefullColumn&&this.getTile(i,column).isOccupied();
        }
        return havefullColumn;
    }
    public boolean HaveFullRow(int row) throws OutOfMatrixException{
        boolean havefullRow = true;
        int i=0;
        for( i=0; i<5;i++){
            havefullRow = havefullRow&&this.getTile(row,i).isOccupied();
        }
        return havefullRow;
    }
    public SchemeCard getTwinCard() {
        return this.twinCard;
    }

    public Dice removeDice(int row, int column)throws DiceNotExistantException,OutOfMatrixException{
        return this.getTile(row,column).getandremoveDice() ;
    }
    public DiceColor getColorConstrain( int row, int column){
        return this.matrix[row][column].getColor_Constrain();
    }
    public int getNumberConstrain(int row, int column){
        return this.matrix[row][column].getNumber_Constrain();
    }


    //metodi private
    // lo setto private per non esporre l'implementazione
    private Tile getTile(int row, int column)throws OutOfMatrixException{
        if(row <0 || row > 3 || column <0 || column >4){
            throw new OutOfMatrixException();
        }
        return this.matrix[row][column];
    }
     private boolean EmptyScheme(){
        boolean empty = true;
        try{
            for (int row=0; row < 4; row ++){
                for(int column = 0; column <5; column ++){
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


     //NB: ho scelto di farla come classe anonima perchè boh, mi piaceva di più
    //ah ecco, perchè noi stiamo dando un iteratore solo dentro le classi schemecard, non un iteratore in generale, come affo ha fatto
    //l'override è dovuto al fatto che implemento iterable
    //per le classi anonime pag. 333

    @Override
    public Iterator<Tile> iterator() {
        return new Iterator<Tile>() {
            private int currentRow, currentColumn;
            private boolean deadEnd = false;



            @Override
            public boolean hasNext() {
                return !deadEnd;
            }

            @Override
            public Tile next() {
                Tile nextElement;
                try {
                    nextElement = getTile(currentRow, currentColumn);
                } catch (OutOfMatrixException e) {
                    throw new NoSuchElementException("Matrix dead end reached");
                }

                currentColumn++;
                if (currentColumn == 4) {
                    currentColumn = 0;
                    currentRow++;
                    if (currentRow == 3) {
                        deadEnd = true;
                    }
                }

                return nextElement;
            }


            @Override
            public void remove(){

            }
        };
    }
}

