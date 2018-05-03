package it.polimi.ingsw.model.SchemeDeck;
import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException;

import java.io.*;
import java.lang.String;

import static it.polimi.ingsw.model.DiceColor.GREEN;
import static it.polimi.ingsw.model.DiceColor.VIOLET;

//classe completa

public class SchemeCard{
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
    public void setDice (Dice dice, int row, int column, boolean IgnoreColor, boolean IgnoreNumber)throws TileConstrainException {
        this.getTile(row,column).setDice(dice, IgnoreColor, IgnoreNumber);
    }
    private Tile getTile(int row, int column){
        return this.matrix[row][column];
    }

    public  DiceColor getDiceColour (int row, int column)throws DiceNotExistantException{
        return this.getTile(row, column).getDice().getColor();
    }
    public int getDiceIntensity(int row, int column)throws DiceNotExistantException{
        return this.getTile(row, column).getDice().getIntensity();
    }



    //può essere utile questa classe?
    public boolean HaveFullColumn(int column){
        boolean havefullColumn = true;
        int i=0;
        for( i=0; i<4;i++){
            havefullColumn = havefullColumn&&this.getTile(i,column).isOccupied();
        }
        return havefullColumn;
    }
    public boolean HaveFullRow(int row){
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
    public Dice removeDice(int row, int column)throws DiceNotExistantException{
        return this.matrix[row][column].getandremoveDice() ;
    }
    public DiceColor getColorConstrain( int row, int column){
        return this.matrix[row][column].getColor_Constrain();
    }
    public int getNumberConstrain(int row, int column){
        return this.matrix[row][column].getNumber_Constrain();
    }

}

