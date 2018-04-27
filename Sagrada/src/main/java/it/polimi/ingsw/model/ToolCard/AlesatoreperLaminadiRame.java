package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.TileException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class AlesatoreperLaminadiRame  implements ToolAction {
    final static int ID = 3;
    final static String cardTitle = "Alesatore per lamina di rame";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";
    private Player player;
    private int selectedDiceIndex;
    private int operation, row, column, newRow, newColumn;
    Dice remuvedDice;

    public AlesatoreperLaminadiRame(Player player, int row, int column, int newRow, int newColumn){
        this.player = player;
        this.row = row;
        this.column = column;
        this.newColumn = newColumn;
        this.newRow = newRow;
    }

    @Override

    public void execute () throws ToolIllegalOperationException {
    try {
        remuvedDice = player.getScheme().removeDice(row, column);
        player.getScheme().setDice(remuvedDice, newRow, newColumn,false ,true);
        throw new ToolIllegalOperationException();
    } catch  (ToolIllegalOperationException e){
        throw e;
    } catch (TileException e) {
        e.printStackTrace();
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
