package it.polimi.ingsw.model;
//obiettivo pubblico
public class SfumatureScure implements GoalCard {
    static int ID = 7;
    static String name = "Sfumature Scure";
    static String description = "Set di 5 & 6 ovunque.";
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
