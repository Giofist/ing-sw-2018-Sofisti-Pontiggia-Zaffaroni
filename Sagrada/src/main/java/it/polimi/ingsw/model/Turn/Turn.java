package it.polimi.ingsw.model.Turn;

import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.*;
import it.polimi.ingsw.model.Round;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

public class Turn implements Runnable{

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
    @Override
    public synchronized void run(){
        currentPlayer.setTurn(this);
        if (currentPlayer.mustpassTurn()){
            try {
                currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
            } catch (RemoteException e1) {
                this.doneSignal.countDown();
            }
        }else {
            try {
                currentPlayer.setPlayerState(State.STARTTURNSTATE);
            } catch (RemoteException e2) {
                this.doneSignal.countDown();
            }
        }
        for (Player player: this.round.getMatch().getallPlayersbutnotme(currentPlayer)) {
            try{
                player.setPlayerState(State.NOTYOURTURNSTATE);
            } catch (RemoteException e){
                this.doneSignal.countDown();
                }
            }

        // se è rimasto un solo giocatore, la partita finisce immediatamente
        if (this.doneSignal.getCount() == 1){
            //currentPlayer.getMatch().forceendmatch();
        }

        try{
            doneSignal.await();
        }catch(InterruptedException e){
            // finisco qui se non ricevo un passturn da tutti
            System.out.println("Sono stato interrotto");
        }

        return;
    }

    public int getTurnID() {
        return turnID;
    }
    public void countDown(){
        this.doneSignal.countDown();
    }
}
