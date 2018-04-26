package it.polimi.ingsw.model;
//obiettivo pubblico
public class SfumatureMedie implements GoalCard {
    static int ID = 6;
    static String name = "Sfumature Medie";
    static String description = "Set di 3 & 4 ocunque.";

    @Override
    public void calculatepoint(Player player) {
        //not implemented yet
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
