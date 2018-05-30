package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerPastaSaldaException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PennelloPerPastaSalda2 extends ToolAction{

    public PennelloPerPastaSalda2(){
        this.cost = 0;
        this.ID = 62;
        this.cardTitle = "Pennello per Pasta Salda parte seconda";
        this.description = "Devi piazzere il dado.\n";
    }

    @Override
    public void execute(Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        //ricordarsi sempre di fare get and remove
        try{
            player.getScheme().setDice(player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDIceIndex()), toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, false);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDIceIndex());
            player.setMustsetdice(false);
        }catch (OutOfMatrixException e){
            throw new PennelloPerPastaSaldaException(PennelloPerPastaSaldaException.getMsg()+ e.getMessage());
        }catch (TileConstrainException e){
            throw new PennelloPerPastaSaldaException(PennelloPerPastaSaldaException.getMsg()+ e.getMessage());
        }catch (SchemeCardNotExistantException e){

        }catch (EmpyDicepoolException e){

        }
    }

}
