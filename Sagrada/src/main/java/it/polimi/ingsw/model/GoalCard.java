package it.polimi.ingsw.model;

public interface GoalCard {
    void calculatepoint(Player player);
    public int getID();
    public String getName();
    public String getDescription();
}
