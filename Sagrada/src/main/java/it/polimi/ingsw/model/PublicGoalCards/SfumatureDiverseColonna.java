package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameShadeException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//revisionata by pon
//obiettivo pubblico
public class SfumatureDiverseColonna implements GoalCard,Serializable {
    static int ID = 4;

    @Override
    public void  calculatepoint(Player player) {

        try{
            ColumnIterator columnIterator =  player.getScheme().columnIterator(0);

            while(columnIterator.hasNext()) {
                try {
                    List<Integer> existingshades = new ArrayList<Integer>();
                    RowIterator rowIterator = player.getScheme().rowIterator(columnIterator.getCurrentColumn());
                    while(rowIterator.hasNext()) {
                        int diceintensity = rowIterator.next().getDice().getIntensity();
                        if (existingshades.contains(diceintensity)) {
                            throw new TwoDiceSameShadeException();
                        } else {
                            existingshades.add(diceintensity);
                        }
                    }
                    //add the points for this column, which has respected the constrain
                    player.addPoints(4);
                    columnIterator.next();
                } catch (TwoDiceSameShadeException e) {
                    columnIterator.next();
                    //unfortunately you can't get the points
                }catch (DiceNotExistantException e){
                    columnIterator.next();

                    //unfortunately you can't get the points
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
