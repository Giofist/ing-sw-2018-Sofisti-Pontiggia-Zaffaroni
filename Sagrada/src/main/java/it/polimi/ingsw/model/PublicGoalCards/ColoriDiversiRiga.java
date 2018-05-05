package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.LinkedList;
import java.util.List;

// revisionata by pon
//obiettivo pubblico
public class ColoriDiversiRiga implements GoalCard {
    static int ID = 1;
    static String name = "Colori Diversi - Riga";
    static String description = "Righe senza colori ripetuti.";

    @Override
    public void  calculatepoint(Player player) {
        int row = 0;
        for (row = 0; row < 4; row++) {
            try {
                List<DiceColor> existingcolors = new LinkedList<DiceColor>();
                for (int column= 0; column < 5; column++) {
                    if (existingcolors.contains(player.getScheme().getDiceColour(row, column))) {
                        throw new TwoDiceSameColorException();
                    } else {
                        existingcolors.add(player.getScheme().getDiceColour(row, column));
                    }
                }
                    player.addPoints(6);
                } catch (TwoDiceSameColorException e) {
                    //unfortunately you can't get the points: there are two dices of the same color
                }catch (DiceNotExistantException er){
                // unfortunately you can't get the points
                }catch (OutOfMatrixException err){
                //impossibile che accada, sto iterando correttamente
            }
            }
    }


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
