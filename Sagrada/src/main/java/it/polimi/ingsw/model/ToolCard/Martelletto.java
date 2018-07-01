package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.MartellettoException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;

public class Martelletto  extends ToolAction implements Serializable{

    public Martelletto(){
        this.cost =1;
        this.ID =7;
    }


    /**
     * This method allows to execute the effect of "Martelletto"
     *
     * Tool request class parameters necessary for the execution are:
     * - No specific parameter is required
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException{
        //se non lancia eccezioni ci siamo dimenticati di qualcosa
        try{
            if (player.getTurn().getTurnID() == 1){
                throw new MartellettoException("14.1");
            }
            if(player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                throw new MartellettoException("14.2");
            }
            for(int i=0; i<player.getGametable().getRoundDicepool().getDicePoolSize();i++)
                player.getGametable().getRoundDicepool().getDice(i).setRandomIntensity();
            player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch (DicepoolIndexException e){
            throw new MartellettoException();
        }

    }


}
