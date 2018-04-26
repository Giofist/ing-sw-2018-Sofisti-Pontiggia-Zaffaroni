package it.polimi.ingsw.model;
//obiettivo pubblico
public class SfumatureDiverseRiga implements GoalCard {
    static int ID = 3;
    static String name = "Sfumature diverse - Riga";
    static String description = "Righe senza sfumature ripetute.";
    @Override
    public int calculatepoint(Player player) {
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
