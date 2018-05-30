package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.DiluentePerPastaSalda2Exception;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionato by pon
public class DiluentePerPastaSalda2 extends ToolAction{
    public DiluentePerPastaSalda2(){
        this.cost=0;
        this.ID = 112;
        this.cardTitle = "Diluente per Pasta Salda";
        this.description = "Parte Seconda.\n" +
                "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";

    }
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        try{

            player.getdiceforDiluenteperPastaSalda().setIntensity(toolRequestClass.getDiceIntesityToset());
            player.getScheme().setDice(player.getdiceforDiluenteperPastaSalda(), toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, false);
            // mi piace separare la set dalla remove
            player.removediceforDiluenteperPastaSalda();
        }catch (DiceNotExistantException e){
            throw new  DiluentePerPastaSalda2Exception(DiluentePerPastaSalda2Exception.getMsg() +e.getMsg());
        }catch (OutOfMatrixException e){
            throw  new DiluentePerPastaSalda2Exception(DiluentePerPastaSalda2Exception.getMsg()+ e.getMsg());
        }catch (TileConstrainException e){
            throw new DiluentePerPastaSalda2Exception(DiluentePerPastaSalda2Exception.getMsg()+ e.getMsg());
        }catch(SchemeCardNotExistantException e){
            //do nothing
        }

    }
}
