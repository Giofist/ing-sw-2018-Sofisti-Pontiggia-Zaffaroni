package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.LathekinException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;


public class Lathekin  extends ToolAction implements Serializable{

    public Lathekin(){
        this.cost =1;
        this.ID =4;
    }
    Dice removedDice1, removedDice2;


    /**
     * This method allows to execute the effect of "Lathekin"
     *
     * Tool request class parameters necessary for the execution are:
     * - oldRow1
     * - oldColumn1
     * - newRow1
     * - newColumn1
     * - oldRow2
     * - oldColumn2
     * - newRow2
     * - newColumn2
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            removedDice1 = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().setDice(removedDice1, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, false);
        }catch( Exception e){
            try{
                player.getScheme().setDice(removedDice1, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), false, false, false );
            }catch(Exception err){
                //do nothing
            }
            throw new LathekinException();
        }
        try{
            removedDice2 = player.getScheme().getDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
            player.getScheme().removeDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
            player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(),false,false, false);
        }catch( Exception e){
            try{
                player.getScheme().removeDice(toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1());
                player.getScheme().setDice(removedDice1, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(),true,true, true);
                player.getScheme().setDice(removedDice2, toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2(),true,true, true);
            }catch(Exception err){
                //do nothing
            }
            throw new LathekinException();
        }
        if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
            player.setPlayerState(State.MUSTPASSTURNSTATE);
        }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);


    }


}
