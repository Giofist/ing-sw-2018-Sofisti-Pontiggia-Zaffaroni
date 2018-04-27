package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo pubblico
public class SfumatureDiverseColonna implements GoalCard {
    static int ID = 4;
    static String name = "Sfumature Diverse - Colonna";
    static String description = "Colonne senza sfumature ripetute.";

    @Override
    public void  calculatepoint(Player player) {
        int[] counter;
        counter = new int[6];
        int nCol=0;
        int numDice=0;
        boolean allDiff=true;

        for(int row=0; row<4; row++) {
            for (int column = 0; column < 5; column++) {
                counter[player.getScheme().getDiceIntensity(row, column)-1]++;
            }
            for(int i=0; i<6 && allDiff; i++){
                if(counter[i]<=1){
                    numDice+=counter[i];
                }
                else allDiff = false;
            }
            if(numDice==4&&allDiff){
                nCol++;
            }
            allDiff=true;
        }
        player.addPoints(nCol*4);
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
