package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.rmi.RemoteException;
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
        for (Player player: this.round.getMatch().getallPlayers()){
            player.setTurn(this);
        }
        try {
            if (currentPlayer.mustpassTurn()){
                currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
            }else {
                currentPlayer.setPlayerState(State.STARTTURNSTATE);
            }
        }catch (RemoteException e1) {
            try{
                UsersList.Singleton().getUser(currentPlayer.getName()).setActive(false);
            }catch(Exception err){
                //do nothing
            }
            this.doneSignal.countDown();
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
            currentPlayer.getMatch().forceendmatch();
        }

        try{
            doneSignal.await();
        }catch(InterruptedException e){
            // finisco qui se non ricevo un passturn da tutti
            System.out.println("Sono stato interrotto");
        }
        synchronized (this.round){
            this.round.notify();
        }
        return;
    }

    public int getTurnID() {
        return turnID;
    }
    public void countDown(){
        this.doneSignal.countDown();
    }

    public void countDownAll() {
        while(this.doneSignal.getCount() != 0){
            this.doneSignal.countDown();
        }
    }

    // Useful for testing
    protected long getCountDownValue() {
        return this.doneSignal.getCount();
    }
}
