package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.MartellettoException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Martelletto  extends ToolAction implements Serializable{
    public Martelletto(){
        this.cost =1;
        this.ID =7;
    }

    @Override

    public void execute (Player player, ToolRequestClass toolRequestClass)throws ToolIllegalOperationException{
        //se non lancia eccezioni ci siamo dimenticati di qualcosa
        try{
            if (player.getTurn().getTurnID() == 1){
                throw new MartellettoException("14.1");
            }
            if(player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                throw new MartellettoException("14.2");
            }
            for(int i=0; i<player.getGametable().getRoundDicepool().getDicePoolSize();i++)
                player.getGametable().getRoundDicepool().getDice(i).setRandomIntensity();
            try{
                player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
            }catch (RemoteException e){
                try{
                    UsersList.Singleton().getUser(player.getName()).setActive(false);
                }catch(Exception err){
                    //do nothing
                }
                player.getTurn().countDown();
            }
        }catch (DicepoolIndexException e){
            throw new MartellettoException();
        }

    }


}
