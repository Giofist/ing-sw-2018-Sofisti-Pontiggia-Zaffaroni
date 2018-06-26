package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

public class TamponeDiamantato extends ToolAction implements Serializable {

    public TamponeDiamantato(){
        this.cost =1;
        this.ID =10;
    }

    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()).setOppositeIntensity();
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(DicepoolIndexException e){
            throw new ToolIllegalOperationException();
        }
    }


}
