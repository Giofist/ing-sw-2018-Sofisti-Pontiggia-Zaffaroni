package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PennelloperEglomise  implements ToolAction {

    final static int ID = 2;
    final static String cardTitle = "Pennello per Eglomise";
    final static String description = "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore.\n" +
                                      "Devi rispettare tutte le altre restrizioni di piazzamento.";
    private Player player;
    private int row, column, newRow, newColumn;

    public PennelloperEglomise(Player player, int row, int column, int newRow, int newColumn){
        this.player = player;
        this.row = row;
        this.column = column;
        this.newRow = newRow;
        this.newColumn = newColumn;
    }

    @Override

    public void execute () throws ToolIllegalOperationException{
        try{
            Dice removedDice = player.getScheme().getDice(row, column);
            player.getScheme().setDice(removedDice, newRow, newColumn, true, false, false);
            player.getScheme().removeDice(row,column);
        }catch(DiceNotExistantException e) {
            throw new PennelloPerEglomiseException(PennelloPerEglomiseException.getMsg() + e.getMessage());
        }catch (OutOfMatrixException e){
            throw new PennelloPerEglomiseException(PennelloPerEglomiseException.getMsg()+ e.getMessage());
        }catch (TileConstrainException e){
            throw new PennelloPerEglomiseException(PennelloPerEglomiseException.getMsg()+ e.getMessage());
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
