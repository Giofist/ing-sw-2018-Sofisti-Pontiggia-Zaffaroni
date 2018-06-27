package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.RigaInSugheroException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;

//revisionata by pon
public class RigainSughero  extends ToolAction implements Serializable {
    private Dice dice;
    public RigainSughero(){
        this.cost = 1;
        this.ID=9;

    }


    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        //ricordarsi di fare get and remove dei dadi, non dimenticare la remove
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
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)) {
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            } else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
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
