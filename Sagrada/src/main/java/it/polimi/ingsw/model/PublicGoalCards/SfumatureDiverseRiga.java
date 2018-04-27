package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo pubblico
public class SfumatureDiverseRiga implements GoalCard {
    static int ID = 3;
    static String name = "Sfumature diverse - Riga";
    static String description = "Righe senza sfumature ripetute.";

    @Override
    public void  calculatepoint(Player player) {
        int[] counter;
        counter = new int[6];
        int nRow=0;
        int numDice=0;
        boolean allDiff=true;

        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                counter[player.getScheme().getDiceIntensity(row, column)-1]++; //-1 così posiziono in counter[0]
            }
            for(int i=0; i<6 && allDiff; i++){
                if(counter[i]<=1){
                    numDice+=counter[i];
                }
                else allDiff = false;
            }
            if(numDice==5&&allDiff){
                nRow++;
            }
            allDiff=true;
        }
        player.addPoints(nRow*5);
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
