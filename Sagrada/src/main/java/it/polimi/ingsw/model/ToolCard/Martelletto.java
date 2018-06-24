package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.MartellettoException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;

public class Martelletto  extends ToolAction {
    public Martelletto(){
        this.cost =1;
        this.ID =7;
        this.cardTitle = "Martelletto";
        this.description = "Tira nuovamente tutti i dadi della riserva.\n" +
                "Questa carta può essere usata solo durante il tuo secondo turno, prima di scegliere il secondo dado.";

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
                player.getAssociatedUser().setActive(false);
                player.getTurn().countDown();
            }
        }catch (DicepoolIndexException e){
            throw new MartellettoException();
        }

    }


}
