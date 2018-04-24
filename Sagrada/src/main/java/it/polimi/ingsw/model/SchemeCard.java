package it.polimi.ingsw.model;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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

        //si dovranno inserire tutti i metodi di cui qui sotto

        //inizialise difficulty
        String fileName = "Maps.txt";
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {

            //SCANDISCO IL FILE FINO ALLA RIGA DESIDERATA
            for (int j = 0; j < mapID * 3 + 1; j++) {
                buffer.readLine();
            }
            //VARIABILE DI SUPPORTO
            char[] map = new char[20];
            map = buffer.readLine().toCharArray();

            //read map array
            this.MapName = buffer.readLine();

            //setDifficulty
            this.difficulty = Integer.parseInt(buffer.readLine());

            //set Tiles
            for(int a=0; a<5; a++) {
                for (int b = 0; b < 4; b++) {
                    Tile tile = this.matrix[a][b];
                    tile.setFree();
                    switch (map[a * 5 + b]) {
                        case 'Y':
                            tile.setColourConstrain(DiceColor.YELLOW);
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

<<<<<<< HEAD
=======






/*
    public static void main(String args[])
            throws IOException {
        int numOfMaps = 0, ran = 0, diff1 = 0, diff2 = 0, numMap = 0;
        Random random = new Random();
        String nameMap1, nameMap2, fileName="Maps.txt";
        char[] map1, map2;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            numOfMaps = Integer.parseInt(br.readLine());
            ran = random.nextInt(numOfMaps / 2);
            System.out.println(ran);
            System.out.println(numOfMaps);
            for (int i = 0; i < (ran * 6); ) {    //numOfMaps devided by 2 return the number of cuple of cards I have
                br.readLine();
                i++;
            }
            nameMap1 = br.readLine();
            diff1 = Integer.parseInt(br.readLine());
            map1 = br.readLine().toCharArray();
            nameMap2 = br.readLine();
            diff2 = Integer.parseInt(br.readLine());
            map2 = br.readLine().toCharArray();
            showMap(map1, nameMap1, diff1);
            showMap(map2, nameMap2, diff2);
            numMap = choseMap();
        }
    }
    private static int choseMap() {        //read from the command line the map chosen
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose the map: (1/2)\n");
        return scanner.nextInt();
    }
    private static void showMap(char[] map, String name, int diff) {     //drow on the prompt map passed by arg
        int i = 0, j = 0;
        System.out.println(name);
        for(; j < diff ;j++){
            System.out.print("*");
        }
        System.out.print("\n___________\n");
        for (; i < 20; i++) {
            System.out.print("|");
            System.out.print(map[i]);
            if (((i + 1) % 5) == 0) {
                System.out.print("|\n-----------\n");
            }
        }
    }
}
*/
>>>>>>> b89eade308160ab584fff7af619d167e26b3733e
