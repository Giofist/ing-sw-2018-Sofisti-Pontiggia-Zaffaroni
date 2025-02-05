package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TenagliaRotelleException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;

//scusate questa non l'ho capita
public class TenagliaRotelle  extends ToolAction implements Serializable {

    public TenagliaRotelle(){
        this.cost = 1;
        this.ID =8;
    }


    /**
     * This method allows to execute the effect of "Tenaglia a Rotelle"
     *
     * Tool request class parameters necessary for the execution are:
     * - selectedDiceIndex
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        if(player.getTurn().getTurnID()==2){
            throw new TenagliaRotelleException("21.1");
        }
        if(!player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
            throw new TenagliaRotelleException("21.2");
        }
        try{
            player.getScheme().setDice(  player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()), toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false,false,false);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());
            player.setMustpassTurn(true);
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(TileConstrainException e){
            throw new TenagliaRotelleException();
        }catch(OutOfMatrixException e){
            throw new TenagliaRotelleException();
        }catch (SchemeCardNotExistantException e){
            // do something?
        }catch (DicepoolIndexException e){
            //do something?
        }
    }



}
