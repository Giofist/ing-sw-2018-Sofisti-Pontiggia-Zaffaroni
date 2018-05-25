package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//the user can upload a map he has kindly prepared for us only before the game starts
public class SchemeCardDeck {
    private ArrayList<Integer> maps;

    public SchemeCardDeck() throws IOException{
        this.maps = new ArrayList<>();
        for(int i=0; i<getNumMaps();i=i+2){
            this.maps.add(i);
        }
        Collections.shuffle(maps);
    }

    //the most important, it will be invoked by the gametable
    public SchemeCard getCard () throws IOException,MapConstrainReadingException {
        int mapID = this.getRandomID();
        return new SchemeCard(mapID);

    }
    // it returns a random value (well... the first, but we have shuffled before)
    // wich tells us which map load from the file
    //we delete that value from the arraylist because a map is only for one player
    private int getRandomID(){
        int value = this.maps.get(0);
        this.maps.remove(0);
        return value;
    }

    //this is to get the number of maps uploaded by the player + the standards one
    private static int getNumMaps() throws IOException {
        String fileName = "C:\\Users\\ponti\\Documents\\GitHub\\ing-sw-2018-Sofisti-Pontiggia-Zaffaroni\\Sagrada\\src\\main\\java\\it\\polimi\\ingsw\\model\\Maps.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s = br.readLine();
            return Integer.parseInt(s);
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

}
