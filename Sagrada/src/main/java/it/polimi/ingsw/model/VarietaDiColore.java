package it.polimi.ingsw.model;
//obiettivo pubblico
public class VarietaDiColore implements GoalCard {
    static int ID = 10;
    static String name = "Varietà di Colore";
    static String description = "Set di dadi di ogni colore ovunque.";
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
