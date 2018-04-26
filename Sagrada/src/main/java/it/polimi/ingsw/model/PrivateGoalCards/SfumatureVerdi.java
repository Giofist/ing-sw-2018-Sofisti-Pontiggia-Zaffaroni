package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureVerdi implements GoalCard {
    static private int ID = 3;
    static private String name = "Sfumature Verdi";
    static private String description = "Somma dei valori su tutti i dadi verdi.";


    @Override
    public void calculatepoint(Player player) {
        //not implemented yet
    }

    @Override
    public  int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public  String getDescription() {
        return description;
    }
}
