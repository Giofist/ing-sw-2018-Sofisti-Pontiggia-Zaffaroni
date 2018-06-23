package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.EmpyDicepoolException;
import it.polimi.ingsw.model.Exceptions.OutOfMatrixException;
import it.polimi.ingsw.model.Exceptions.SchemeCardNotExistantException;
import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TenagliaRotelleException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;

//scusate questa non l'ho capita
public class TenagliaRotelle  extends ToolAction {

    public TenagliaRotelle(){
        this.cost = 1;
        this.ID =8;
        this.cardTitle = "Tenaglia a Rotelle";
        this.description =  "Dopo il tuo primo turno scegli immediatamente un altro dado.\n" +
                "Salta il secondo turno di questo round.";
    }
    @Override

    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        if(player.getTurn().getTurnID()==2){
            throw new TenagliaRotelleException("sei al secondo turno: non puoi usare questa carta al secondo turno!");
        }
        if(!player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
            throw new ToolIllegalOperationException("puoi usare questa carta solo alla fine del turno");
        }
        try{
            player.getScheme().setDice(  player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedRoundDicepoolDiceIndex()), toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false,false,false  );
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedRoundDicepoolDiceIndex());
            player.setMustpassTurn(true);
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(TileConstrainException e){
            throw new TenagliaRotelleException(TenagliaRotelleException.getMsg()+e.getMessage());
        }catch(OutOfMatrixException e){
            throw new TenagliaRotelleException(TenagliaRotelleException.getMsg()+e.getMessage());
        }catch (SchemeCardNotExistantException e){

        }catch (EmpyDicepoolException e){

        }catch (RemoteException e){
            player.getAssociatedUser().setActive(false);
            player.getTurn().countDown();
        }

    }



}
