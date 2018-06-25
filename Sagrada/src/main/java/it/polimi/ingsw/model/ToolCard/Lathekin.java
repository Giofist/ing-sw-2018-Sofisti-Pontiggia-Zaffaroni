package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.LathekinException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

//revisionata by pon
public class Lathekin  extends ToolAction implements Serializable{


    public Lathekin(){
        this.cost =1;
        this.ID =4;
    }
    Dice removedDice1, removedDice2;

    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            removedDice1 = player.getScheme().getDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().removeDice(toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1());
            player.getScheme().setDice(removedDice1, toolRequestClass.getNewRow1(), toolRequestClass.getNewColumn1(), false, false, false);
        }catch( Exception e){
            try{
                player.getScheme().setDice(removedDice1, toolRequestClass.getOldRow1(), toolRequestClass.getOldColumn1(), false, false, false );
            }catch(Exception err){
                //do nothing
            }
            throw new LathekinException();
        }
        try{
            removedDice2 = player.getScheme().getDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
            player.getScheme().removeDice(toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2());
            player.getScheme().setDice(removedDice2, toolRequestClass.getNewRow2(), toolRequestClass.getNewColumn2(),false,false, false);
        }catch( Exception e){
            try{
                player.getScheme().setDice(removedDice2, toolRequestClass.getOldRow2(), toolRequestClass.getOldColumn2(),false,false, false);
            }catch(Exception err){
                //do nothing
            }
            throw new LathekinException();
        }
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

    }


}
