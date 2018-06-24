package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

//fatta da pon
public class FreeSpaces implements GoalCard {
    @Override
    public void calculatepoint(Player player) {
        try {
            for (Tile tile : player.getScheme()) {
                if (!tile.isOccupied()) {
                    player.addPoints(-1);
                }
            }
        } catch (SchemeCardNotExistantException e){
            //??
        }
    }

    @Override
    public int getID() {
        return -1;
    }

}
