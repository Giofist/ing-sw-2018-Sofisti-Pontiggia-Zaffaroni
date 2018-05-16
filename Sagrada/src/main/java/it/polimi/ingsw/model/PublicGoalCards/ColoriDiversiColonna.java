package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//obiettivo pubblico
//revisionata by pon
public class ColoriDiversiColonna implements GoalCard {
    static int ID = 2;
    static String name = "Colori Diversi - Colonna";
    static String description = "Colonne senza colori ripetuti.";


    @Override
    public void  calculatepoint(Player player) {
        for (int column = 0; column < 5; column++) {
            try {
                List<DiceColor> existingcolors = new LinkedList<DiceColor>();
                for (int row = 0; row < 4; row++) {
                    if (existingcolors.contains(player.getScheme().getDiceColour(row, column))) {
                        throw new TwoDiceSameColorException();
                    } else {
                        existingcolors.add(player.getScheme().getDiceColour(row, column));
                    }
                }
                player.addPoints(5);
            } catch (TwoDiceSameColorException e) {
                //unfortunately you can't get the points: there are two dices of the same color
            }catch (DiceNotExistantException e){
                //unfortunately you can't get the point: the column has an empty tile
            }catch ( OutOfMatrixException e){
                //no way to get here
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
