package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.RigaInSugheroException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;

public class RigainSughero  extends ToolAction implements Serializable {
    private Dice dice;
    public RigainSughero(){
        this.cost = 1;
        this.ID=9;

    }


    /**
     * This method allows to execute the effect of "Riga in sughero"
     *
     * Tool request class parameters necessary for the execution are:
     * - selectedDiceIndex
     * - newRow1
     * - newColumn1
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try {
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)) {
                throw new RigaInSugheroException("18.2");
            }
            dice = player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex());
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());
            boolean thereisadicenearyou = player.getScheme().ThereisaDicenearYou(toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1());
            if (thereisadicenearyou) {
                throw new RigaInSugheroException("18.1");
            } else
                player.getScheme().setDice(dice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, true);
            player.setPlayerState(State.MUSTPASSTURNSTATE);
        }catch (Exception e){
            try{
                player.getGametable().getRoundDicepool().addDice(toolRequestClass.getSelectedDiceIndex(),dice);
            }catch(Exception er){
                //do nothing
            }
            throw new RigaInSugheroException();
        }


        //not implemented yet
    }


}
