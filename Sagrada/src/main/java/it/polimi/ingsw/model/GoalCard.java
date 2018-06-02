package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PlayerPackage.Player;

public interface GoalCard {
    public void calculatepoint(Player player);
    public int getID();
    public String getName();
    public String getDescription();
}
