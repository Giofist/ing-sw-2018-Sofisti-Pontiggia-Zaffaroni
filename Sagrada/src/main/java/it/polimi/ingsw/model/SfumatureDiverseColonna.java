package it.polimi.ingsw.model;
//obiettivo pubblico
public class SfumatureDiverseColonna implements GoalCard {
    static int ID = 4;
    static String name = "Sfumature Diverse - Colonna"
    static String description = "Colonne senza sfumature ripetute.";
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
