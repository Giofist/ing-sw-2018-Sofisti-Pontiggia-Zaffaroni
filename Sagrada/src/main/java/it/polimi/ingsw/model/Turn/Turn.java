package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Round;

import java.rmi.RemoteException;
import java.util.List;

public class Turn {

    private Player currentPlayer;
    private Round round;

    public Turn(Player player, Round round) {
        this.currentPlayer = player;
        this.round = round;
    }

    public synchronized void run(){
        if (currentPlayer.mustpassTurn()){
            currentPlayer.setPlayerState(new MustPassTurnState());
        }else{
            currentPlayer.setPlayerState(new StartTurnState());
        }
        currentPlayer.setTurn(this);
        currentPlayer.notifyIsYourTurn();
        try{
            wait();
        }catch(InterruptedException e){
            // do nothing
        }

        currentPlayer.setPlayerState(new NotYourTurnState());


    }
}
