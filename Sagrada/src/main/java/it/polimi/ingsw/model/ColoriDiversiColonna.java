package it.polimi.ingsw.model;
//obiettivo pubblico
public class ColoriDiversiColonna implements GoalCard {
    static int ID = 2;
    static String name = "Colori Diversi - Colonna";
    static String description = "Colonne senza colori ripetuti.";
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
