package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// revisionata by pon: lo stile era troppo procedurale
//obiettivo pubblico
public class SfumatureDiverseRiga implements GoalCard,Serializable {
    static int ID = 3;

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

    @Override
    public int getID() {
        return ID;
    }

}
