package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureVerdi implements GoalCard {
    static private int ID = 3;
    static private String name = "Sfumature Verdi";
    static private String description = "Somma dei valori su tutti i dadi verdi.";


    @Override
    public int calculatepoint(Player player) {
        int greenPoint=0;
        DiceColor color = DiceColor.GREEN;
        for(int column=0; column<4; column++){
            for(int row=0; row<5; row++) {
                if (player.getScheme().getDiceColour(row, column)== color) {
                    greenPoint += player.getScheme().getDiceIntensity(row, column);
                }
            }
        }
        return greenPoint;
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
