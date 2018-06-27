package it.polimi.ingsw.model.PrivateGoalCards;


import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.GoalCard;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchemeDeck.Tile;

import java.io.Serializable;

//obiettivo privato
public class Sfumature implements GoalCard, Serializable {
    private int ID;
    private transient DiceColor color;

    public Sfumature(int id, DiceColor diceColor){
        this.color = diceColor;
        this.ID = id;

    }

    @Override
    public void calculatepoint(Player player) {
        try{
            for(Tile tile: player.getScheme()) {
                try{
                    if (tile.getDice().getColor()== color) {
                        player.addPoints(tile.getDice().getIntensity());
                    }
                }catch (DiceNotExistantException e){
                    //zorry, there is no dice
                    //
                }
            }
        } catch (SchemeCardNotExistantException e){
            //??
        }
    }

    @Override
    public  int getID() {
        return ID;
    }


}

