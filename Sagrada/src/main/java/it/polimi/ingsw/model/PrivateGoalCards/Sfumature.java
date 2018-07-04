package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;


/**
 * This is the class for the private goal cards
 */
public class Sfumature implements GoalCard, Serializable {
    private int ID;
    private transient DiceColor color;


    /**
     * @param id The id corresponding to the private goal card corresponding to its title and description
     * @param diceColor Which color we want to create the private goal card
     */
    public Sfumature(int id, DiceColor diceColor){
        this.color = diceColor;
        this.ID = id;
    }


    /**
     * This method implements the command pattern to calculate the score for the player for this type of goal card
     * @param player A player for whom we want to calculate the score
     */
    @Override
    public void calculatepoint(Player player) {
        try{
            for(Tile tile: player.getScheme()) {
                try{
                    if (tile.getDice().getColor()== color) {
                        player.addPoints(tile.getDice().getIntensity());
                        player.addPrivateGoalCardpoints(tile.getDice().getIntensity());
                    }
                }catch (DiceNotExistantException e){
                    // sorry, there is no dice
                }
            }
        } catch (SchemeCardNotExistantException e){
            e.getMessage();
        }
    }


    /**
     * @return The id associated to the card
     */
    @Override
    public  int getID() {
        return ID;
    }


}

