package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

public class SpaziLiberi implements GoalCard {
    @Override
    public void calculatepoint(Player player) {

    }

    @Override
    public int getID() {
        return 999;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "A point less for every";
    }
}
