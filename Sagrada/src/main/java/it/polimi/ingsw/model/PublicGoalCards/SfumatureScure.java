package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo pubblico
public class SfumatureScure implements GoalCard {
    static int ID = 7;
    static String name = "Sfumature Scure";
    static String description = "Set di 5 & 6 ovunque.";

    @Override
    public void  calculatepoint(Player player) {
        int[] counter;   // array che conta le occorrenze
        counter = new int[6];
        int nRow=0;
        int min=4; // inizializzo a 4 ma massima possibile combinazione è 3 da 1 a 6

        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                counter[player.getScheme().getDiceIntensity(row, column)-1]++;
            }
        }

        for(int i=4; i<6; i++){ // verifico numero massimo di set presenti
            if(counter[i]<min){
                min=counter[i];
            }
        }
        player.addPoints(min*2);
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public String getDescription() {
        return description;
    }
}