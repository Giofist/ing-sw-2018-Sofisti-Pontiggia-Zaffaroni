package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;

public class PennelloperPastaSalda  extends ToolAction {


    public PennelloperPastaSalda(){
        this.cost =1;
        this.ID =6;
        this.cardTitle = "Pennello per Pasta Salda";
        this.description = "Dopo aver scelto un dado tira nuovamente quel dado.\n" +
                "Se non puoi piazzarlo, riponilo nella riserva.";
    }


    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        //ricordarsi sempre di fare gt and remove
        try{
            Dice dice= player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDIceIndex());
            dice.setRandomIntensity();
            if(player.HassetaDicethisturn()){
                throw new ToolIllegalOperationException("non puoi piazzare due dadi nello stesso turno");
            }
            boolean settable = false;
            RowIterator rowIterator =  player.getScheme().rowIterator(0);
            while(rowIterator.hasNext()) {
                ColumnIterator columnIterator = player.getScheme().columnIterator(rowIterator.getCurrentRow());
                while(columnIterator.hasNext()){
                    settable = settable || player.getScheme().SettableHere(dice, rowIterator.getCurrentRow(),columnIterator.getCurrentColumn(),false, false);
                }
            }
            if(settable){
            player.setDiceforToolCardUse(dice);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDIceIndex());
            player.setPlayerState(State.MUSTSETPENNELLOPERPASTASALDASTATE);
            }else{
                if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                    player.setPlayerState(State.MUSTPASSTURNSTATE);
                }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
            }

        }catch (EmpyDicepoolException e){
            throw new ToolIllegalOperationException(e.getMessage());
        }catch (SchemeCardNotExistantException e){
            throw new ToolIllegalOperationException(e.getMessage());
        }

    }



}
