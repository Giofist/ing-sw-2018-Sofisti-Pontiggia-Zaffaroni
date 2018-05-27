package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameShadeException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;

import java.util.ArrayList;
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
        try{
            RowIterator rowIterator =  player.getScheme().rowIterator(0);
            // do nothing
            while(rowIterator.hasNext()) {
                try {
                    List<Integer> existingcolors = new ArrayList<>();
                    ColumnIterator columnIterator = player.getScheme().columnIterator(rowIterator.getCurrentRow());
                    while(columnIterator.hasNext()) {
                        int  diceIntensity = columnIterator.next().getDice().getIntensity();
                        if (existingcolors.contains(diceIntensity)) {
                            throw new TwoDiceSameColorException();
                        } else {
                            existingcolors.add(diceIntensity);
                        }
                    }
                    player.addPoints(5);
                    rowIterator.next();
                } catch (TwoDiceSameColorException e) {
                    //unfortunately you can't get the points: there are two dices of the same color
                } catch (DiceNotExistantException e) {
                    //unfortunately you can't get the point: the column has an empty tile
                }
            }
        }catch(SchemeCardNotExistantException e){
            //do nothing
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
