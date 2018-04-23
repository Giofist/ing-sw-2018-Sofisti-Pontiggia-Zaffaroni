package it.polimi.ingsw.model;

import java.io.*;
import java.lang.String;


public class SchemeCard {
    Tile[5][4];


    public SchemeCard() {
    }

    public int getNumMaps() throws IOException {     //return the num of maps contained into the file Maps.txt (first line of teh file)
        String fileName = "Maps.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return Integer.parseInt(br.readLine());
        }
    }

    public int getDifficulty(int i) throws IOException {   //return the difficulty of the map, the second line of each description of Maps.txt
        String fileName = "Maps.txt";
        try (BufferedReader ba = new BufferedReader(new FileReader(fileName))) {
            for (int j = 0; j < i * 3 + 2; j++) {    //6 line for each couple +1 for the first line + 1 to have the diff line
                ba.readLine();
            }
            return Integer.parseInt(ba.readLine());
        }
    }


    public String getName ( int i) throws IOException { //return the name of the map first line of description of each map into the file
        String fileName = "Maps.txt";

        try (BufferedReader bo = new BufferedReader(new FileReader(fileName))) {
            for (int j = 0; j < i * 3 + 1; j++) {    //6 line for each couple +1 for the first line
                bo.readLine();
            }
            return bo.readLine();
        }
    }

    public SchemeCard getMatrix ( int i) throws IOException{ //read from file the map constraints and then cast to an array that is transformed into a bi-dimensional matrix
        String fileName = "Maps.txt";
        char[] map;
        Tile[][] matrix;
        matrix = new Tile[5][4];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int j = 0; j < i * 3 + 3; j++) {    //6 line for each couple +1 for the first line
                br.readLine();
            }
            map = br.readLine().toCharArray();
                 for(int a=0; a<5; a++){
                    for(int b=0; b<4; b++){
                        matrix[a][b]= new Tile;
                        Tile.setFree();
                       switch{
                           case (map[a*5+b]==Y) {
                               Tile.setColourCostrain(Yellow);
                           }
                            case (map[a*5+b]==B) {
                                Tile.setColourCostrain(Blue);
                            }
                            case (map[a*5+b]==P) {
                                Tile.setColourCostrain(Purple);
                            }
                            case (map[a*5+b]==G) {
                                Tile.setColourCostrain(Green);
                            }
                            case (map[a*5+b]==Y) {
                                Tile.setColourCostrain(Yellow);
                            }
                            case (map[a*5+b]==Y) {
                                Tile.setColourCostrain(Yellow);
                            }
                    }
                }
            }
        }


        //public SchemeCard getTwinSchemeCard(){return twin;}

        //setTwinCard useless because you chose into the file txt

        //public Tile getTile ( int x, int y){
        // return map[(y - 1) * 5 + x *];
        //}


    }
}







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
