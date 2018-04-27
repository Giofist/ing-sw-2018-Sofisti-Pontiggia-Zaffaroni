package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//obiettivo pubblico
//TODO classe da rifare
public class ColoriDiversiColonna implements GoalCard {
    static int ID = 2;
    static String name = "Colori Diversi - Colonna";
    static String description = "Colonne senza colori ripetuti.";

    @Override
    public void  calculatepoint(Player player) {
        int column = 0;
        for (column = 0; column < 5; column++) {
            if (player.getScheme().HaveFullColumn(column)) {
                try {
                    List<DiceColor> existingcolors = new LinkedList<DiceColor>();
                    for (int row = 0; row < 4; row++) {
                        if (existingcolors.contains(player.getScheme().getDiceColour(row, column))) {
                            throw new Exception();
                        } else {
                            existingcolors.add(player.getScheme().getDiceColour(row, column));
                        }
                    }
                    player.addPoints(5);
                } catch (Exception e) {
                    //unfortunately you can't get the points
                }
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName(){return name;}

    @Override
    public String getDescription() {
        return description;
    }
}
