package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionata by pon
public class AlesatoreperLaminadiRame  implements ToolAction {
    final static int ID = 3;
    final static String cardTitle = "Alesatore per lamina di rame";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;
    private int operation, row, column, newRow, newColumn;
    Dice removedDice;

    public AlesatoreperLaminadiRame(Player player, int row, int column, int newRow, int newColumn){
        this.player = player;
        this.row = row;
        this.column = column;
        this.newColumn = newColumn;
        this.newRow = newRow;
    }


    @Override

    public void execute () throws  ToolIllegalOperationException {

        Dice dice = player.getScheme().getDice(row, column);
        // no dice, no way to move it
        try {
            player.getScheme().setDice(dice, newRow, newColumn, false, true);
        } catch (TileConstrainException e) {
            //if I've violated a constrain, first of all i need to put the dice in his old position
            //then I give back bad news to the caller
            player.getScheme().setDice(dice, this.row, this.column, false, false);
            throw e;
        }
    }


    @Override
    public int getID(){
        return ID;
    }
    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String getCardTitle() { return cardTitle; }
}
