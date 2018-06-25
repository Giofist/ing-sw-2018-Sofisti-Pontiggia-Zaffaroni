package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;

//revisionata by pon
//obiettivo pubblico
public class SfumatureScure implements GoalCard,Serializable {
    static int ID = 7;

    @Override
    public void  calculatepoint(Player player) {
        int numerodi5=0;
        int numerodi6=0;
        try{
            for(Tile tile: player.getScheme()) {
                try{
                    if(tile.getDice().getIntensity()==5){
                        numerodi5++;
                    }else if(tile.getDice().getIntensity()==6){
                        numerodi6++;
                    }
                }catch (Exception e){
                    //no dice, do point
                }
            }
        }catch(SchemeCardNotExistantException e){
            //do nothing
        }
        if (numerodi5 <= numerodi6) {
            player.addPoints(numerodi5*2);
        }else{
            player.addPoints(numerodi6*2);
        }


    }

    @Override
    public int getID() {
        return ID;
    }

}
