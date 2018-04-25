package it.polimi.ingsw.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class SchemeCardDeck {
    private ArrayList<Integer> maps = new ArrayList<>();

    public SchemeCardDeck() throws IOException {
        this.maps = new ArrayList<>();
        for(int i=0; i<getNumMaps();i=i+2){
            this.maps.add(i);
        }
        Collections.shuffle(maps);
    }

    //the most important, it will be invoked by the gametable
    public SchemeCard getCard () throws IOException{
        int mapID = this.getRandomID();
        SchemeCard schemeCard = new SchemeCard(mapID);
        return schemeCard;

    }
    // it returns a random value (well... the first, but we have shuffled before)
    // wich tells us which map load from the file
    //we delete that value from the arraylist because a map is only for one player
    public int getRandomID(){
        int value = this.maps.get(0);
        this.maps.remove(0);
        return value;
    }

    //this is to get the number of maps uploaded by the player + the standards one
    public  static int getNumMaps() throws IOException {
        String fileName = "Maps.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return Integer.parseInt(br.readLine());
        }
    }
}