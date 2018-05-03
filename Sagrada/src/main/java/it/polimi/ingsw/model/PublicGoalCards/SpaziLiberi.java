package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

public class SpaziLiberi implements GoalCard {
    @Override
    public void calculatepoint(Player player) {
        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                //try{
                    //player.getScheme().setDice();
                //}catch (DiceNotExistantException e){
                    //no dice, no point
                //}
            }
        }

    }

    @Override
    public int getID() {
        return 999;
    }

    @Override
    public String getName() {
        return "Spazi Bianchi";
    }

    @Override
    public String getDescription() {
        return "A point less for every blank space in your window ";
    }
}
