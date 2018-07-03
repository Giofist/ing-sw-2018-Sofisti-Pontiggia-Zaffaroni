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

// revisionata by pon
//obiettivo pubblico
public class ColoriDiversiRiga implements GoalCard,Serializable {
    static int ID = 1;


    /**
     * @param player A player for whom we want to calculate the points scored in this goal
     */
    @Override
    public void  calculatepoint(Player player) {

        try{
            RowIterator rowIterator =  player.getScheme().rowIterator(0);
            while(rowIterator.hasNext()) {
                try {
                    List<DiceColor> existingcolors = new LinkedList<DiceColor>();
                    ColumnIterator columnIterator = player.getScheme().columnIterator(rowIterator.getCurrentRow());
                    while(columnIterator.hasNext()) {
                        DiceColor diceColor = columnIterator.next().getDice().getColor();
                        if (existingcolors.contains(diceColor)) {
                            throw new TwoDiceSameColorException();
                        } else {
                            existingcolors.add(diceColor);
                        }
                    }
                    player.addPoints(5);
                    rowIterator.next();
                } catch (TwoDiceSameColorException e) {
                    rowIterator.next();

                    //unfortunately you can't get the points: there are two dices of the same color
                } catch (DiceNotExistantException e) {
                    rowIterator.next();

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
