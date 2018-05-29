package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DiceNotExistantException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.DiluentePerPastaSalda2Exception;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.Player;
//revisionato by pon
public class DiluentePerPastaSalda2 implements ToolAction{
    final static int ID = 0;
    final static String cardTitle = "Diluente per Pasta Salda";
    final static String description = "Parte Seconda.\n" +
            "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento.";


    int row;
    int column;
    int diceIntesityToset;
    public DiluentePerPastaSalda2( int row, int column, int diceIntesityToset){
        this.row = row;
        this.column = column;
        this.diceIntesityToset = diceIntesityToset;
    }


    @Override
    public void execute (Player player) throws ToolIllegalOperationException{
        try{

            player.getdiceforDiluenteperPastaSalda().setIntensity(this.diceIntesityToset);
            player.getScheme().setDice(player.getdiceforDiluenteperPastaSalda(),this.row, this.column, false, false, false);
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

    @Override
    public int getID(){
        return ID;
    }
    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String getCardTitle() {
        return cardTitle;
    }

}
