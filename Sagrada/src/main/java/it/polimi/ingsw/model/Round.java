package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.RoundTrackException;

import java.util.*;

//not implemented yet
public class Round {
    private List<Player> players;

    /**
     * @param players List of all the players that are playing
     */
    public Round ( List<Player> players){
        this.players = players;
    }


    /**
     * This is the main method which controls the whole round
     */
    public synchronized void run() {
        // First turn in a round
        for (Player player: this.players) {
            Turn turn = new Turn(player,1);
            final Thread thread = new Thread(turn);
            Timer timer  = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (thread.isAlive()) {
                        turn.countDownAll();
                    }
                }
            },180000);
            try{
                thread.start();
                wait();
            }catch (InterruptedException e){
                //do nothing
            }
        }

        // Second turn in a round
        Collections.reverse(this.players);
        for (Player player: this.players){
            Turn turn = new Turn(player,2);
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

    }





}
