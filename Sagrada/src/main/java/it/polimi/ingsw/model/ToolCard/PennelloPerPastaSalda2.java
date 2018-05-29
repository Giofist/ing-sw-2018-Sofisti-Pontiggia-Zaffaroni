package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerPastaSaldaException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;

public class PennelloPerPastaSalda2 implements ToolAction{
    final static int ID = 0;
    final static String cardTitle = "Pennello per Pasta Salda parte seconda";
    final static String description =
            "Devi piazzere il dado.\n";

    @Override
    public void execute(Player player, RequestClass requestClass) throws ToolIllegalOperationException{
        //ricordarsi sempre di fare get and remove
        try{
            player.getScheme().setDice(player.getGametable().getRoundDicepool().getDice(requestClass.getSelectedDIceIndex()),requestClass.getNewRow1(), requestClass.getNewColumn1(), false, false, false);
            player.getGametable().getRoundDicepool().removeDice(requestClass.getSelectedDIceIndex());
            player.setMustsetdice(false);
        }catch (OutOfMatrixException e){
            throw new PennelloPerPastaSaldaException(PennelloPerPastaSaldaException.getMsg()+ e.getMessage());
        }catch (TileConstrainException e){
            throw new PennelloPerPastaSaldaException(PennelloPerPastaSaldaException.getMsg()+ e.getMessage());
        }catch (SchemeCardNotExistantException e){

        }catch (EmpyDicepoolException e){

        }
    }
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCardTitle() {
        return cardTitle;
    }

}
