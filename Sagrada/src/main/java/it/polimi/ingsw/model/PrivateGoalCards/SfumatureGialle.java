package it.polimi.ingsw.model.PrivateGoalCards;


//obiettivo privato
public class SfumatureGialle implements GoalCard {
    static private int ID = 2;
    static private String name = "Sfumature Gialle";
    static private String description = "Somma dei valori su tutti i dadi gialli.";


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
