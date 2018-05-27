package it.polimi.ingsw.model.PublicGoalCards;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TwoDiceSameColorException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
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
