package it.polimi.ingsw.model.ToolCard;


import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Exceptions.DicepoolIndexException;
import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.TaglierinaCircolareException;
import it.polimi.ingsw.model.Exceptions.ToolIllegalOperationExceptions.ToolIllegalOperationException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;
import it.polimi.ingsw.model.UsersList;

import java.io.Serializable;
import java.rmi.RemoteException;


//per implementare questa classe bisogna prima pensare al traccaito round
public class TaglierinaCircolare  extends ToolAction implements Serializable {

    public TaglierinaCircolare(){
        this.cost=1;
        this.ID =5;
    }
    @Override
    public void execute (Player player, ToolRequestClass toolRequestClass) throws ToolIllegalOperationException {
        try {
            Dice RoundDicepooldice = player.getGametable().getRoundDicepool().getDice(toolRequestClass.getSelectedDiceIndex());
            Dice RoundTrackdice = player.getGametable().getRoundTrack().getroundTrackDices(toolRequestClass.getRoundWhereThediceis()).getDice(toolRequestClass.getSelectedRoundTrackDiceIndex());
            player.getGametable().getRoundTrack().getroundTrackDices(toolRequestClass.getRoundWhereThediceis()).addDice(RoundDicepooldice);
            player.getGametable().getRoundDicepool().removeDice(toolRequestClass.getSelectedDiceIndex());
            player.getGametable().getRoundDicepool().addDice(RoundTrackdice);
            player.getGametable().getRoundTrack().getroundTrackDices(toolRequestClass.getRoundWhereThediceis()).removeDice(toolRequestClass.getSelectedRoundTrackDiceIndex());
            if (player.getPlayerState().getState().equals(State.HASSETADICESTATE)){
                player.setPlayerState(State.MUSTPASSTURNSTATE);
            }else player.setPlayerState(State.HASUSEDATOOLCARDACTIONSTATE);
        }catch(RoundTrackException e){
            throw new TaglierinaCircolareException();
        }catch (DicepoolIndexException e){
            //do something?
        }catch(RemoteException e){
            try{
                UsersList.Singleton().getUser(player.getName()).setActive(false);
            }catch(Exception err){
                //do nothing
            }
            player.getTurn().countDown();
        }

    }



}
