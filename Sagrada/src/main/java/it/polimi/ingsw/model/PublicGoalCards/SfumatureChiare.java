package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;


public class SfumatureChiare implements GoalCard,Serializable {
    static int ID = 5;


    /**
     * @param player A player for whom we want to calculate the points scored in this goal
     */
    @Override
    public void  calculatepoint(Player player) {
        int numerodi1 =0;
        int numerodi2=0;
        try{
            for(Tile tile: player.getScheme()) {
                try{
                    if(tile.getDice().getIntensity()==1){
                        numerodi1++;
                    }
                    if(tile.getDice().getIntensity()==2){
                        numerodi2++;
                    }
                }catch (Exception e){
                    //no dice, no point
                }
            }
        }catch(SchemeCardNotExistantException e){
            //do nothing
        }
        if (numerodi1 <= numerodi2) {
            player.addPoints(numerodi1*2);
        }else{
            player.addPoints(numerodi2*2);
        }
    }


    /**
     * @return The id of the public goal card
     */
    @Override
    public int getID() {
        return ID;
    }

}
