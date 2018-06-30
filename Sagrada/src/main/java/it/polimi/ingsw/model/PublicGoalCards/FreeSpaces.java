package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;


/**
 * This public goal card is not really a goal card but a rule that needs to be considered
 * by each player when calculating the final score
 */
public class FreeSpaces implements GoalCard {


    /**
     * @param player A player for whom we want to calculate the points scored in this goal
     */
    @Override
    public void calculatepoint(Player player) {
        try {
            for (Tile tile : player.getScheme()) {
                if (!tile.isOccupied()) {
                    player.addPoints(-1);
                }
            }
        } catch (SchemeCardNotExistantException e){
        }
    }


    /**
     * @return Always -1
     */
    @Override
    public int getID() {
        return -1;
    }

}
