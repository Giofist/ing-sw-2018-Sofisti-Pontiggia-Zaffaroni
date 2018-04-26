package it.polimi.ingsw.model;
//obiettivo pubblico
public class ColoriDiversiRiga implements GoalCard {
    static int ID = 1;
    static String name = "Colori Diversi - Riga";
    static String description = "Righe senza colori ripetuti.";
    @Override
    public void calculatepoint(Player player) {
        //not implemented yet
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
