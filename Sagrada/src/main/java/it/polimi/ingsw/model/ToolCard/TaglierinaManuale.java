package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.DiceColor;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaManualeException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.State;

import java.io.Serializable;
import java.util.List;


public class TaglierinaManuale  extends ToolAction implements Serializable{
    public TaglierinaManuale(){
        this.cost = 1;
        this.ID =12;
    }
    Dice removedDice;
    Dice removedDice2;


    /**
     * This method allows to execute the effect of "Taglierina Manuale"
     *
     * Tool request class parameters necessary for the execution are:
     * - numberofDicesyouwanttomove
     * - oldRow1
     * - oldColumn1
     * - newRow1
     * - newColumn1
     * - oldRow2 (optional if number of dice to move is 2)
     * - oldColumn2 (optional if number of dice to move is 2)
     * - newRow2 (optional if number of dice to move is 2)
     * - newColumn2 (optional if number of dice to move is 2)
     *
     * @param player The player that wants to use the tool card
     * @param toolRequestClass The class with all the necessary parameters for the tool card
     * @throws ToolIllegalOperationException Exception thrown in case some constrain is not respected or in case the player performs an illegal operation
     */
    @Override
    public void execute(Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        try {
            removedDice = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            List<DiceColor> diceColors = player.getGametable().getRoundTrack().allColors();
            if (diceColors.contains(removedDice.getColor())) {
                player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
                player.getScheme().setDice(removedDice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, false);
            } else {
                throw new TaglierinaManualeException("20.2");
            }
        } catch (Exception e) {
            try {
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), true, true, true);
            } catch (Exception err) {
                // Do Nothing
            }

            throw new TaglierinaManualeException();
        }

        try {
            if (toolRequestClass.getNumberofDicesyouwanttomove() == 2) {
                removedDice2 = player.getScheme().getDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
                if (removedDice2.getColor() == removedDice.getColor()) {
                    player.getScheme().removeDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
                    player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(), false, false, false);
                } else {
                    throw new TaglierinaManualeException("20.1");
                }
            }
        } catch (Exception e) {
            try {
                player.getScheme().removeDice(toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1());
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), true, true, true);
                player.getScheme().setDice(removedDice2, toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2(), true, true, true);
            } catch (Exception err) {
                // Do Nothing
            }

            throw new TaglierinaManualeException();
        }

        if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
            player.setPlayerState(State.MUSTPASSTURNSTATE);
        }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);

    }

}
