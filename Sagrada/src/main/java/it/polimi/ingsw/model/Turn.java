package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.UserNotExistentException;

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
        if (currentPlayer.mustpassTurn()){
            currentPlayer.setPlayerState(State.NOTYOURTURNSTATE);
        }else{
            currentPlayer.setPlayerState(State.STARTTURNSTATE);
        }
        try{
            if (!UsersList.Singleton().findUser(currentPlayer.getName()).isActive()) {
                this.countDown();
            }
        }catch(UserNotExistentException e){
                //do nothing
        }

        for (Player player: this.round.getMatch().getallPlayersbutnotme(currentPlayer)) {
            player.setPlayerState(State.NOTYOURTURNSTATE);
            try{
                if (!UsersList.Singleton().findUser(player.getName()).isActive()) {
                    this.countDown();
                }
            }catch(UserNotExistentException e){
                //do nothing
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
