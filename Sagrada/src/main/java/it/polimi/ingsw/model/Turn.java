package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.UserNotExistentException;

import java.time.format.SignStyle;
import java.util.concurrent.CountDownLatch;

public class Turn implements Runnable{

    private Player currentPlayer;
    private Round round;
    private int turnID;
    CountDownLatch doneSignal ;

    /**
     * When a new Turn is created also a countdown gets instantiated to keep track of when every player is ready to pass its turn
     * @param player The player who has to perform its turn
     * @param round The round in which we are
     * @param turnID The number of the turn in the round
     */
    public Turn(Player player, Round round, int turnID) {
        this.currentPlayer = player;
        this.round = round;
        this.turnID = turnID;
        doneSignal = new CountDownLatch(player.getMatch().getNumberOfPlayers());
    }


    /**
     * Main method in which the state of each player in the match is set whether it needs to play or wait
     */
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
                    System.out.println("Ho trovato inattivo "+ player.getName() + " nel turno "+ this.turnID);
                    this.countDown();
                }
            }catch(UserNotExistentException e){
                //do nothing
            }
        }
        // If there is only one player the match terminates immediately
        if (this.doneSignal.getCount() == 1){
            currentPlayer.getMatch().forceendmatch();
        }

        try{
            doneSignal.await();
        }catch(InterruptedException e){
            // We end up here if the wound doesn't receive a "passturn" action from everybody
            System.out.println("Sono stato interrotto");
        }
        synchronized (this.round){
            this.round.notify();
        }
        return;
    }


    /**
     * @return The turn at which the round is
     */
    public int getTurnID() {
        return turnID;
    }


    /**
     * This method decrements by one a countdown used to keep track when each player is ready to pass to the next turn
     */
    public void countDown(){
        this.doneSignal.countDown();
    }


    /**
     * This method brings to zero a countdown used to keep track when each player is ready to pass to the next turn.
     * By calling this match we pass to the next turn.
     */
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
