package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

//revisionata by pon
//obiettivo pubblico
public class SfumatureChiare implements GoalCard {
    static int ID = 5;
    static String name = "Sfumature Chiare";
    static String description = "Set di 1 & 2 ovunque.";


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

    @Override
    public String getName(){return name;}

    @Override
    public String getDescription() {
        return description;
    }
}
