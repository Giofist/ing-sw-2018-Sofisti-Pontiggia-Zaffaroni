package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PennelloPerEglomiseException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

public class PennelloperEglomise extends ToolAction implements Serializable {

    private Dice removedDice;
    public PennelloperEglomise(){
        this.cost =1;
        this.ID=2;

    }

    @Override

    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException{
        try{
             removedDice = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().setDice(removedDice, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), true, false, false);
            try{
                if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                    player.setPlayerState(State.MUSTPASSTURNSTATE);
                }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
            }catch (RemoteException e){
                try{
                    UsersList.Singleton().getUser(player.getName()).setActive(false);
                }catch(Exception err){
                    //do nothing
                }
                player.getTurn().countDown();
            }

        }catch(Exception e) {
            try{
                player.getScheme().setDice(removedDice, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), true, false, false);
            }catch(Exception er){
                //do nothing, sorry!
            }
            throw new PennelloPerEglomiseException();
        }
    }
}
