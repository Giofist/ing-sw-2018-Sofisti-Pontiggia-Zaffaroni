package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
//revisionata by pon
//obiettivo pubblico
public class SfumatureScure implements GoalCard {
    static int ID = 7;
    static String name = "Sfumature Scure";
    static String description = "Set di 5 & 6 ovunque.";

    @Override
    public void  calculatepoint(Player player) {
        int numerodi5=0;
        int numerodi6=0;

        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                try{
                    if(player.getScheme().getDiceIntensity(row, column)==5){
                        numerodi5++;
                    }else
                    if(player.getScheme().getDiceIntensity(row, column)==6){
                        numerodi6++;
                    }
                }catch (Exception e){
                    //no dice, do point
                }
            }
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

    @Override
    public String getName(){return name;}

    @Override
    public String getDescription() {
        return description;
    }
}
