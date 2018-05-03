package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

public class FreeSpaces implements GoalCard {
    @Override
    public void calculatepoint(Player player) {
        for(int column=0; column<5; column++) {
            for (int row = 0; row < 4; row++) {
                if(!player.getScheme().IsTileOccupied(row,column)){
                    player.addPoints(-1);
                }

            }
        }

    }

    @Override
    public int getID() {
        return -1;
    }

    @Override
    public String getName() {
        return "Free Spaces";
    }

    @Override
    public String getDescription() {
        return "A point less for every blank space in your window ";
    }
}
