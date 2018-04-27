package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureBlu implements GoalCard {
    static private int ID = 4;
    static private String name = "Sfumature Blu";
    static private String description = "Somma dei valori su tutti i dadi blu.";


    //attenzione: la carta schema ha cinque colonne e quattro righe
    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.BLUE;
        for(int row=0; row<4; row++){
            for(int column=0; column<5; column++) {
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
