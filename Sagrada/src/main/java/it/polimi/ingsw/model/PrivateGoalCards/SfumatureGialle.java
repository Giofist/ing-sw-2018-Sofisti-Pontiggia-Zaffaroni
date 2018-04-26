package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

//obiettivo privato
public class SfumatureGialle implements GoalCard {
    static private int ID = 2;
    static private String name = "Sfumature Gialle";
    static private String description = "Somma dei valori su tutti i dadi gialli.";
    public int point=0;


    @Override
    public void calculatepoint(Player player) {
        DiceColor color = DiceColor.YELLOW;
        for(int column=0; column<4; column++){
            for(int row=0; row<5; row++) {
                if (player.getScheme().getDiceColour(row, column)== color) {
                    this.point += player.getScheme().getDiceIntensity(row, column);
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

    public int getPoint(){return this.point;}

}

