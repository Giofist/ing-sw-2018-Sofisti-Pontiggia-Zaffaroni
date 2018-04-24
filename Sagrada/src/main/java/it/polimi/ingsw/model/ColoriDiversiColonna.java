package it.polimi.ingsw.model;
//obiettivo pubblico
public class ColoriDiversiColonna implements GoalCard {
    static int ID = 2;
    static String description = "";
    @Override
    public void calculatepoint(Player player) {
        //not implemented yet
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
