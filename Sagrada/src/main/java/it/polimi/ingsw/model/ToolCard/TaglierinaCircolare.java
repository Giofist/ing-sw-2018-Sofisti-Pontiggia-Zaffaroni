package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaCircolareException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;


public class TaglierinaCircolare  extends ToolAction implements Serializable {

    public TaglierinaCircolare(){
        this.cost=1;
        this.ID =5;
    }


    /**
     * This method allows to execute the effect of "Taglierina Circolare"
     *
     * Tool request class parameters necessary for the execution are:
     * - selectedDiceIndex
     * - roundWhereThediceis
     * - selectedRoundTrackDiceIndex
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try {
            Dice RoundDicepooldice = player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex());
            Dice RoundTrackdice = player.getGametable().getRoundTrack().getroundTrackDices(toolRequestClass.getRoundWhereThediceis()).getDice(toolRequestClass.getSelectedRoundTrackDiceIndex());
            player.getGametable().getRoundTrack().getroundTrackDices(toolRequestClass.getRoundWhereThediceis()).addDice(RoundDicepooldice);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());
            player.getGametable().getRoundDicepool().addDice(RoundTrackdice);
            player.getGametable().getRoundTrack().getroundTrackDices(toolRequestClass.getRoundWhereThediceis()).removeDice(toolRequestClass.getSelectedRoundTrackDiceIndex());
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(RoundTrackException e){
            throw new TaglierinaCircolareException();
        }catch (DicepoolIndexException e){
            //do something?
        }
    }



}
