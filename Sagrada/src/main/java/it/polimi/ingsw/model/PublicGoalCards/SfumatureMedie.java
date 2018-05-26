package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

//revisionata by pon
//obiettivo pubblico
public class SfumatureMedie implements GoalCard {
    static int ID = 6;
    static String name = "Sfumature Medie";
    static String description = "Set di 3 & 4 ovunque.";

    @Override
    public void  calculatepoint(Player player) {
        int numerodi3=0;
        int numerodi4=0;
        try{
            for(Tile tile: player.getScheme()) {
                try{
                    if(tile.getDice().getIntensity()==3){
                        numerodi3++;
                    }
                    if(tile.getDice().getIntensity()==4){
                        numerodi4++;
                    }
                }catch (Exception e){
                        //no dice, no point
                }
            }
        }catch(SchemeCardNotExistantException e){
            // do nothing
        }
        if (numerodi3 <= numerodi4) {
            player.addPoints(numerodi3*2);
        }else{
            player.addPoints(numerodi4*2);
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
