package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.util.Arrays;

//revisionata by pon
//obiettivo pubblico
public class SfumatureDiverse implements GoalCard {
    static int ID = 8;
    static String name = "Sfumature Diverse";
    static String description = "Set di dadi di ogni valore ovunque.";

    @Override
    public void  calculatepoint(Player player) {

        // This array counts how many dices of each intensity are placed on the player's SchemeCard
        int[] numberof = new int[6];

        try{
            for(Tile tile: player.getScheme()) {
                try {
                    switch(tile.getDice().getIntensity()){
                        case 1:  numberof[0]++; break;
                        case 2: numberof[1]++; break;
                        case 3: numberof[2]++; break;
                        case 4: numberof[3]++; break;
                        case 5: numberof[4]++; break;
                        case 6: numberof[5]++; break;
                        default:
                    }
                } catch (Exception e) {
                    //no dice, no point, zorry
                }
            }
        }catch(SchemeCardNotExistantException e){
            //do nothing
        }

        Arrays.sort(numberof);

        player.addPoints(numberof[0]*5);
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
