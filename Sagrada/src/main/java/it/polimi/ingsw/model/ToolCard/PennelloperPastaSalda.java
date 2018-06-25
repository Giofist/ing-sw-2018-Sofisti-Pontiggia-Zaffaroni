package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerPastaSaldaException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.SchemeDeck.ColumnIterator;
import it.polimi.ingsw.model.SchemeDeck.RowIterator;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

public class PennelloperPastaSalda  extends ToolAction implements Serializable {


    public PennelloperPastaSalda(){
        this.cost =1;
        this.ID =6;
    }


    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException {
        try{
            Dice dice= player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDIceIndex());
            dice.setRandomIntensity();
            if(player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                throw new PennelloPerPastaSaldaException("16.1");
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
        }catch (DicepoolIndexException e){
            throw new PennelloPerPastaSaldaException();
        }catch (SchemeCardNotExistantException e){
            throw new PennelloPerPastaSaldaException();
        }catch (RemoteException e){
            try{
                UsersList.Singleton().getUser(player.getName()).setActive(false);
            }catch(Exception err){
                //do nothing
            }
            player.getTurn().countDown();
        }

    }



}
