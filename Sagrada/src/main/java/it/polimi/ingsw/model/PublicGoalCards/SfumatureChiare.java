package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import java.lang.Math.*;
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
        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                try{
                    if(player.getScheme().getDiceIntensity(row, column)==1){
                        numerodi1++;
                    }
                    if(player.getScheme().getDiceIntensity(row, column)==2){
                        numerodi2++;
                    }
                }catch (Exception e){
                    //no dice, no point
                }
            }
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
