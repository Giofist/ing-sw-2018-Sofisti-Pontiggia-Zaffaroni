package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 *
 */
public class ColoriDiversiColonna implements GoalCard,Serializable {
    static int ID = 2;

    /**
     * @param player A player for whom we want to calculate the points scored in this goal
     */
    @Override
    public void  calculatepoint(Player player) {
        try{
            ColumnIterator columnIterator =  player.getScheme().columnIterator(0);
            // do nothing
            while(columnIterator.hasNext()) {
                try {
                    List<DiceColor> existingcolors = new LinkedList<DiceColor>();
                    RowIterator rowIterator = player.getScheme().rowIterator(columnIterator.getCurrentColumn());
                    while(rowIterator.hasNext()) {
                        DiceColor diceColor = rowIterator.next().getDice().getColor();
                        if (existingcolors.contains(diceColor)) {
                            throw new TwoDiceSameColorException();
                        } else {
                            existingcolors.add(diceColor);
                        }
                    }
                    player.addPoints(5);
                    columnIterator.next();
                } catch (TwoDiceSameColorException e) {
                    columnIterator.next();
                    //unfortunately you can't get the points: there are two dices of the same color
                } catch (DiceNotExistantException e) {
                    columnIterator.next();

                    //unfortunately you can't get the point: the column has an empty tile
                }
            }
        }catch(SchemeCardNotExistantException e){
            //do nothing
        }
    }


    /**
     * @return The id of the public goal card
     */
    @Override
    public int getID() {
        return ID;
    }


}
