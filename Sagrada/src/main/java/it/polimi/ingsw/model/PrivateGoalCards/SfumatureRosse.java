package it.polimi.ingsw.model.PrivateGoalCards;

import it.polimi.ingsw.model.*;

//obiettivo privato
public class SfumatureRosse implements GoalCard {
    static private int ID = 1;
    static private String name = "Sfumature Rosse";
    static private String description = "Somma dei valori su tutti i dadi rossi.";

    //qui bisognerebbe scambiare righe e colonne
    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.RED;
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
