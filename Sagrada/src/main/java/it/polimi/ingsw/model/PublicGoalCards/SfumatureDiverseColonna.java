package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameShadeException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//revisionata by pon
//obiettivo pubblico
public class SfumatureDiverseColonna implements GoalCard {
    static int ID = 4;
    static String name = "Sfumature Diverse - Colonna";
    static String description = "Colonne senza sfumature ripetute.";

    @Override
    public void  calculatepoint(Player player) {
        int column = 0;
        for (column = 0; column < 5; column++) {
            try {
                List<Integer> existingshades = new ArrayList<Integer>();
                for (int row = 0; row < 4; row++) {
                    if (existingshades.contains(player.getScheme().getDiceIntensity(row, column))) {
                        throw new TwoDiceSameShadeException();
                    } else {
                        existingshades.add(player.getScheme().getDiceIntensity(row, column));
                    }
                }
                //add the points for this column, which has respected the constrain
                player.addPoints(4);
            } catch (TwoDiceSameShadeException e) {
                //unfortunately you can't get the points
            }catch (DiceNotExistantException e){
                //unfortunately you can't get the points
            }catch (OutOfMatrixException e){
                //impossible
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
