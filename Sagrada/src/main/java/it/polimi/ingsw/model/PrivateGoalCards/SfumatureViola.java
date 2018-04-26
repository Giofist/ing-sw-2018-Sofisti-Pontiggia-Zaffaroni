package it.polimi.ingsw.model.PrivateGoalCards;


//obiettivo privato
public class SfumatureBlu implements GoalCard {
    static private int ID = 5;
    static private String name = "Sfumature Blu";
    static private String description = "Somma dei valori su tutti i dadi blu.";


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
