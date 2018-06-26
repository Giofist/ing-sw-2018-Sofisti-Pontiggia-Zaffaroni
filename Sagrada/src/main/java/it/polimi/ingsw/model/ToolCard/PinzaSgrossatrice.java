package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DecreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.IncreaseNotAllowedException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.PinzaSgrossatriceException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;

public class PinzaSgrossatrice  extends ToolAction implements Serializable{



    public PinzaSgrossatrice(){
        this.cost =1;
        this.ID=1;
    }


    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            if (toolRequestClass.getOperationforPinzaSgrossatrice() ==0) {  // Decrease selected dice value
                player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()).decreaseIntensity();
            } else {    // Increase selected dice value
                player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex()).increaseIntensity(); }
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(DecreaseNotAllowedException e){
            throw new PinzaSgrossatriceException("17.24");
        }catch (IncreaseNotAllowedException e){
            throw new PinzaSgrossatriceException("17.29");
        }catch (DicepoolIndexException e){
            //do nothing
        }
    }


}
