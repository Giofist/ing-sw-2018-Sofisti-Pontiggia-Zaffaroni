package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.AlesatorePerLaminadiRameException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionata by pon
public class AlesatoreperLaminadiRame  implements ToolAction {
    int cost;
    final static int ID = 3;
    final static String cardTitle = "Alesatore per lamina di rame";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";


    private int  row, column, newRow, newColumn;
    private Dice removedDice;


    public AlesatoreperLaminadiRame( int row, int column, int newRow, int newColumn){
        cost=1;
        this.row = row;
        this.column = column;
        this.newColumn = newColumn;
        this.newRow = newRow;
    }


    @Override

    public void execute (Player player) throws  ToolIllegalOperationException {
        try{
            removedDice = player.getScheme().getDice(row, column);
            player.getScheme().removeDice(row,column);
            player.getScheme().setDice(removedDice, newRow, newColumn, false, true, false);
        }catch (Exception e) {
            try {
                player.getScheme().setDice(removedDice, row, column, true, false, false);
            } catch (Exception er) {
                //do nothing
            }
            throw new AlesatorePerLaminadiRameException(AlesatorePerLaminadiRameException.getMsg() + e.getMessage());
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
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
