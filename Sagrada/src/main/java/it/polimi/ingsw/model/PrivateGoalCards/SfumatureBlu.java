package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureBlu implements GoalCard {
    static private int ID = 4;
    static private String name = "Sfumature Blu";
    static private String description = "Somma dei valori su tutti i dadi blu.";


    @Override
    public int calculatepoint(Player player) {
        int bluePoint=0;
        DiceColor color = DiceColor.BLUE;
        for(int column=0; column<4; column++){
            for(int row=0; row<5; row++) {
                if (player.getScheme().getDiceColour(row, column)== color) {
                    bluePoint += player.getScheme().getDiceIntensity(row, column);
                }
            }
        }
        return bluePoint;
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
