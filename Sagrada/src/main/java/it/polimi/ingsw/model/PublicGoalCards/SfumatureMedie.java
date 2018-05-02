package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
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

        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                try{
                    if(player.getScheme().getDiceIntensity(row, column)==3){
                        numerodi3++;
                    }
                    if(player.getScheme().getDiceIntensity(row, column)==4){
                        numerodi4++;
                    }
                }catch (DiceNotExistantException e){
                    //no dice, no point
                }
            }
        }
        player.addPoints((int)Math.floor(numerodi3/2)*2);
        player.addPoints((int)Math.floor(numerodi4/2)*2);
        //il metodo floor di java.math arrotonda al decimale inferiore il risultato di numerodi* diviso 2
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
