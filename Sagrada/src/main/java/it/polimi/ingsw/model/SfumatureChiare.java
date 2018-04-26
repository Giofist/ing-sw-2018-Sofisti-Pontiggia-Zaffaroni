package it.polimi.ingsw.model;
//obiettivo pubblico
public class SfumatureChiare implements GoalCard {
    static int ID = 5;
    static String name = "Sfumature Chiare";
    static String description = "Set di 1 & 2 ovunque.";
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
