package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.*;
import it.polimi.ingsw.model.Round;

import java.util.concurrent.CountDownLatch;

public class Turn {

    private Player currentPlayer;
    private Round round;
    private int turnID;
    CountDownLatch doneSignal ;

    public Turn(Player player, Round round, int turnID) {
        this.currentPlayer = player;
        this.round = round;
        this.turnID = turnID;
        doneSignal = new CountDownLatch(player.getMatch().getNumberOfPlayers());
    }

    public synchronized void run(){
        if (currentPlayer.mustpassTurn()){
            currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
        }else{
            currentPlayer.setPlayerState(State.STARTTURNSTATE);
        }
        currentPlayer.setTurn(this);
        currentPlayer.notifyObservers();
        try{
            doneSignal.await();
        }catch(InterruptedException e){
            // do nothing
        }

        currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
        return;
    }

    public int getTurnID() {
        return turnID;
    }
    public void countDown(){
        this.doneSignal.countDown();
    }
}
