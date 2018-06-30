package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;

public class TamponeDiamantato extends ToolAction implements Serializable {

    public TamponeDiamantato(){
        this.cost =1;
        this.ID =10;
    }

    /**
     * This method allows to execute the effect of "Tampone Diamantato"
     *
     * Tool request class parameters necessary for the execution are:
     * - selectedDiceIndex
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()).setOppositeIntensity();
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(DicepoolIndexException e){
            throw new ToolIllegalOperationException();
        }
    }


}
