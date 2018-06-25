package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TenagliaRotelleException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

//scusate questa non l'ho capita
public class TenagliaRotelle  extends ToolAction implements Serializable {

    public TenagliaRotelle(){
        this.cost = 1;
        this.ID =8;
    }
    @Override

    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        if(player.getTurn().getTurnID()==2){
            throw new TenagliaRotelleException("21.1");
        }
        if(!player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
            throw new TenagliaRotelleException("21.2");
        }
        try{
            player.getScheme().setDice(  player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()), toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false,false,false);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());
            player.setMustpassTurn(true);
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(TileConstrainException e){
            throw new TenagliaRotelleException();
        }catch(OutOfMatrixException e){
            throw new TenagliaRotelleException();
        }catch (SchemeCardNotExistantException e){

        }catch (DicepoolIndexException e){

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
