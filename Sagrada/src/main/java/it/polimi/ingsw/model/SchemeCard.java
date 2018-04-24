package it.polimi.ingsw.model;
import java.io.*;
import java.lang.String;

import static it.polimi.ingsw.model.DiceColor.GREEN;
import static it.polimi.ingsw.model.DiceColor.VIOLET;


public class SchemeCard{
    private int difficulty;
    private Tile[][] matrix = new Tile[5][4];
    private SchemeCard twinCard;
    private int ID;
    private String MapName;

    public SchemeCard(int mapID) throws IOException {
        this.inizialize( mapID);
    }

    public void inizialize (int mapID ) throws IOException{



        String fileName = "Maps.txt";
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {

            //SCANDISCO IL FILE FINO ALLA RIGA DESIDERATA
            for (int j = 0; j < mapID * 3 + 1; j++) {
                buffer.readLine();
            }

            //read map array
            this.MapName = buffer.readLine();

            //setDifficulty
            this.difficulty = Integer.parseInt(buffer.readLine());

            //VARIABILE DI SUPPORTO
            char[] map = new char[20];
            map = buffer.readLine().toCharArray();


            //set Tiles
            for(int a=0; a<5; a++) {
                for (int b = 0; b < 4; b++) {
                    Tile tile = this.matrix[a][b];
                    tile.setFree();
                    switch (map[a * 5 + b]) {
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
                        case ' ':

                        default:
                            break; //aggiungere segnale di errore
                    }
                }
            }
        }
        catch (IOException e){
            throw e;
        }

        //alla fine si farà
        this.twinCard = new SchemeCard(mapID+1);
    }


    //get methods
    public int getDifficulty() {
        return this.difficulty; };
    public int getID(){
    return this.ID;
    };
    public String getMapName(){
        return this.MapName;
    }

    public SchemeCard getTwinCard() {
        return twinCard;
    }
    public Tile getTile(int row, int column){
        return this.matrix[row][column];
    }

}

