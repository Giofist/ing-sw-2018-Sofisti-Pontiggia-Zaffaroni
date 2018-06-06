package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PlayerPackage.Player;

public interface GoalCard {
    void calculatepoint(Player player);
    int getID();
    String getName();
    String getDescription();
}
