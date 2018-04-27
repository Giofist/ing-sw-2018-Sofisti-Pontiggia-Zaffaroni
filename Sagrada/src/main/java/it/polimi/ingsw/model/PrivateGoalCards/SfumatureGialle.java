package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureGialle implements GoalCard {
    static private int ID = 2;
    static private String name = "Sfumature Gialle";
    static private String description = "Somma dei valori su tutti i dadi gialli.";



    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.YELLOW;
        for(int column=0; column<5; column++){
            for(int row=0; row<4; row++) {
                if (player.getScheme().getDiceColour(row, column)== color) {
                    player.addPoints(player.getScheme().getDiceIntensity(row, column));
                }
            }
        }
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

