package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.RoundTrackException;

import java.util.*;

//not implemented yet
public class Round {
    private int num_round;
    private LinkedList<Player> players;
    private Match match;


    /**
     * @param num_round The number of round at which the game is
     * @param players List of all the players that are playing
     * @param match The match to which the round belongs
     */
    public Round ( int num_round, LinkedList<Player> players, Match match){
        this.match = match;
        this.num_round = num_round;
        this.players = players;
    }


    /**
     * This is the main method which controls the whole round
     */
    public synchronized void run() {
        // This method prepares the round by extracting the new dices in the round dicepool
        this.getMatch().getGametable().setupRound();

        // First turn in a round
        for (Player player: this.players) {
            Turn turn = new Turn(player, this,1);
            final Thread thread = new Thread(turn);
            Timer timer  = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (thread.isAlive()) {
                        turn.countDownAll();
                    }
                }
            },120000);
            try{
                thread.start();
                wait();
            }catch (InterruptedException e){
                //do notihng
            }
        }

        // Second turn in a round
        Collections.reverse(this.players);
        for (Player player: this.players){
            Turn turn = new Turn(player, this,2);
            final Thread thread = new Thread(turn);
            Timer timer  = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        // Here we can set how many minutes we want to make the round last
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(thread.isAlive()){
                        turn.countDownAll();
                    }
                }
            },0);
            try{
                thread.start();
                wait();
            }catch (InterruptedException e){
                //do notihng
            }
        }

        // Here we reorder the list of the players as it was the beginning of the round
        Collections.reverse(this.players);
        // The round can terminate, all the dices from the RoundDicePool will be moved on the RoundTrack
        try{
            this.getMatch().getGametable().endRound(this.num_round);
        }catch (RoundTrackException e){
            // Situation in which we try to edit a round which is not on the RoundTrack
        }
    }


    // Getters and setters methods


    /**
     * @return The match to which the round belongs
     */
    public Match getMatch() {
        return match;
    }


    /**
     * @return The number of round at which the match is
     */
    public int getNum_round() {
        return num_round;
    }


    /**
     * @return A list of all the players in the round
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }
}
