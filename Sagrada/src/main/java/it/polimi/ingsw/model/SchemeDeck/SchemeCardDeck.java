package it.polimi.ingsw.model.SchemeDeck;

import it.polimi.ingsw.model.Exceptions.MapConstrainReadingException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

//the user can upload a map he has kindly prepared for us only before the game starts
public class SchemeCardDeck {
    private ArrayList<Integer> maps;

    /**
     * This constructor creates a scheme card deck with all the scheme cards with odd index
     * @throws IOException
     */
    public SchemeCardDeck() throws IOException{
        this.maps = new ArrayList<>();
        for(int i=1; i<getNumMaps();i=i+2){
            this.maps.add(i);
        }
        Collections.shuffle(maps);
    }


    /**
     * This method is called by the gametable to extract a random card
     * @return A random scheme card with its twin card on the opposite side
     * @throws IOException Exception thrown when something goes wrong when reading from the file
     * @throws MapConstrainReadingException Exception thrown when the read constrain cannot be interpreted well to create the scheme card
     */
    public SchemeCard getCard () throws IOException,MapConstrainReadingException {
        int mapID = this.getRandomID();
        SchemeCard schemeCard = new SchemeCard(mapID);
        schemeCard.setTwinCard(new SchemeCard(mapID+1));
        return schemeCard;
    }


    /**
     * @return A random odd id of one scheme card
     */
    private int getRandomID(){
        int value = this.maps.get(0);
        this.maps.remove(0);
        return value;
    }


    /**
     * This method reads from a file how many maps are available in the game
     * @return The number of scheme cards available in the game
     * @throws IOException Exception thrown when there is an error when we try to read the value from the file
     */
    protected static int getNumMaps() throws IOException {
        String fileName = "src/main/resources/Maps.txt";

        try  {
            InputStream buffer1 = SchemeCardDeck.class.getClassLoader().getResourceAsStream("Maps.txt");
            InputStreamReader inStrReader = new InputStreamReader(buffer1);
            BufferedReader br = new BufferedReader(inStrReader);

            String s = br.readLine();
            return Integer.parseInt(s);
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

}
