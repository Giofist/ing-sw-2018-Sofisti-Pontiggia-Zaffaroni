package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;

//revisionata by pon
//obiettivo pubblico
public class SfumatureChiare implements GoalCard,Serializable {
    static int ID = 5;


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

    @Override
    public int getID() {
        return ID;
    }

}
