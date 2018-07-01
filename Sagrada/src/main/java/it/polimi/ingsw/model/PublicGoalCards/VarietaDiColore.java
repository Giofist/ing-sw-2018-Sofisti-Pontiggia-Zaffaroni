package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;
import java.util.Arrays;


public class VarietaDiColore implements GoalCard,Serializable {
    static int ID = 10;


    /**
     * @param player A player for whom we want to calculate the points scored in this goal
     */
    @Override
    public void  calculatepoint(Player player) {

        int[] numberof = new int[5];

        try{
            for(Tile tile: player.getScheme()) {
                try {
                    switch(tile.getDice().getColor()){
                        case RED: numberof[0]++; break;
                        case BLUE: numberof[1]++; break;
                        case GREEN: numberof[2]++; break;
                        case YELLOW: numberof[3]++; break;
                        case VIOLET: numberof[4]++; break;
                        default:
                    }

                } catch (Exception e) {
                    //no dice, no point, zorry
                }
            }
        }catch (SchemeCardNotExistantException e){
            // do nothing
        }

        Arrays.sort(numberof);
        player.addPoints(numberof[0]*4);
    }


    /**
     * @return The id of the public goal card
     */
    @Override
    public int getID() {
        return ID;
    }

}
