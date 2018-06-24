package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PlayerPackage.Player;

import java.io.Serializable;

public interface GoalCard {
    void calculatepoint(Player player);
    int getID();
}
