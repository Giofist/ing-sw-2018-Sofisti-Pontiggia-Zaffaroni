package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerPastaSaldaException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;

import java.io.Serializable;

public class PennelloperPastaSalda  extends ToolAction implements Serializable {


    public PennelloperPastaSalda(){
        this.cost =1;
        this.ID =6;
    }


    /**
     * This method allows to execute the effect of "Pennello per Pasta Salda"
     *
     * Tool request class parameters necessary for the execution are:
     * - selectedDiceIndex
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        try{
            Dice dice= player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex());
            dice.setRandomIntensity();
            if(player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                throw new PennelloPerPastaSaldaException("16.1");
            }
            boolean settable = false;
            RowIterator rowIterator =  player.getScheme().rowIterator(0);
            while(rowIterator.hasNext()) {
                ColumnIterator columnIterator = player.getScheme().columnIterator(rowIterator.getCurrentRow());
                while(columnIterator.hasNext()){
                    settable = settable || player.getScheme().SettableHere(dice, rowIterator.getCurrentRow(),columnIterator.getCurrentColumn(),false, false);
                    columnIterator.next();
                }
                rowIterator.next();
            }
            if(settable){
                player.setDiceforToolCardUse(dice);
                player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());
                player.setPlayerState(State.MUSTSETPENNELLOPERPASTASALDASTATE);
            }else{
                if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                    player.setPlayerState(State.MUSTPASSTURNSTATE);
                }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
            }
        }catch (DicepoolIndexException e){
            throw new PennelloPerPastaSaldaException();
        }catch (SchemeCardNotExistantException e){
            throw new PennelloPerPastaSaldaException();
        }
    }



}
