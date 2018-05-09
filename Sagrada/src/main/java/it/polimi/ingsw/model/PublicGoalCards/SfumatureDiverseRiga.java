package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameShadeException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;

import java.util.LinkedList;
import java.util.List;
// revisionata by pon: lo stile era troppo procedurale
//obiettivo pubblico
public class SfumatureDiverseRiga implements GoalCard {
    static int ID = 3;
    static String name = "Sfumature diverse - Riga";
    static String description = "Righe senza sfumature ripetute.";

    @Override
    public void  calculatepoint(Player player) {
        int row = 0;
        for (row = 0; row < 4; row++) {
            try {
                List<Integer> existingshades = new LinkedList<Integer>();
                for (int column= 0; column < 5; column++) {
                    if (existingshades.contains(player.getScheme().getDiceIntensity(row, column))) {
                        throw new TwoDiceSameShadeException();
                    } else {
                        existingshades.add(player.getScheme().getDiceIntensity(row, column));
                    }
                }
                player.addPoints(5);
            } catch (TwoDiceSameShadeException e) {
                //unfortunately you can't get the points
            }catch (Exception er){
                // unfortunately you can't get the points
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
