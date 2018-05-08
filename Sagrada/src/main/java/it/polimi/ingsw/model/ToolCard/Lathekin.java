package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.LathekinException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionata by pon
public class Lathekin  implements ToolAction {
    final static int ID = 4;
    final static String cardTitle = "Lathekin";
    final static String description = "Muovi esattamente due dadi.\n" +
                                      "Rispetta tutte le restrizioni di piazzamento.";
    private Player player;
    int oldRow1, oldColumn1, newRow1, newColumn1, oldRow2, oldColumn2, newRow2, newColumn2;
    Dice removedDice1, removedDice2;

    public Lathekin(Player player, int oldRow1, int oldColumn1, int newRow1, int newColumn1, int oldRow2, int oldColumn2, int newRow2, int newColumn2){
        this.player = player;
        this.oldRow1 = oldRow1;
        this.oldRow2 = oldRow2;
        this.oldColumn1 = oldColumn1;
        this.oldColumn2 = oldColumn2;
        this.newRow1 = newRow1;
        this.newRow2 = newRow2;
        this.newColumn1 = newColumn1;
        this.newColumn2 = newColumn2;
    }

    @Override
    public void execute () throws ToolIllegalOperationException {
        try{
            removedDice1 = player.getScheme().getDice(oldRow1, oldColumn1);
            player.getScheme().setDice(removedDice1, newRow1, newColumn1, false, false, false);
            player.getScheme().removeDice(oldRow1,oldColumn1);
            removedDice2 = player.getScheme().getDice(oldRow2, oldColumn2);
            player.getScheme().setDice(removedDice2, newRow2, newColumn2,false,false, false);
            player.getScheme().removeDice(oldRow2,oldColumn2);
        }catch( DiceNotExistantException e){
            throw new LathekinException(LathekinException.getMsg() + e.getMsg());
        }catch (OutOfMatrixException e){
            throw new LathekinException(LathekinException.getMsg() + e.getMsg());
        }catch (TileConstrainException e){
            throw new LathekinException(LathekinException.getMsg() + e.getMsg());
        }

    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getCardTitle(){return cardTitle;}

    @Override
    public String getDescription(){
        return description;
    }
}
