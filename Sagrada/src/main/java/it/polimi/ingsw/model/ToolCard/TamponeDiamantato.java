package it.polimi.ingsw.model.ToolCard;

import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;

public class TamponeDiamantato extends ToolAction {
    public TamponeDiamantato(){
        this.cost =1;
        this.ID =10;

        this.cardTitle = "Tampone Diamantato";
        this.description = "Dopo aver scelto un dado, giralo sulla faccia opposta.\n" +
                "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc.";

    }
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try{
            player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDIceIndex()).setOppositeIntensity();
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(DicepoolIndexException e){
            throw new ToolIllegalOperationException();
        } catch (RemoteException e) {
            player.getAssociatedUser().setActive(false);
            player.getTurn().countDown();
        } catch (IndexOutOfBoundsException e){
            throw new ToolIllegalOperationException();
        }
    }


}
