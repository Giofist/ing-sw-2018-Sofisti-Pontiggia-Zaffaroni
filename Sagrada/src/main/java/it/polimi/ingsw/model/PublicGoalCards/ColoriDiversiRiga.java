package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo pubblico
public class ColoriDiversiRiga implements GoalCard {
    static int ID = 1;
    static String name = "Colori Diversi - Riga";
    static String description = "Righe senza colori ripetuti.";
    @Override
    public int calculatepoint(Player player) {
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
