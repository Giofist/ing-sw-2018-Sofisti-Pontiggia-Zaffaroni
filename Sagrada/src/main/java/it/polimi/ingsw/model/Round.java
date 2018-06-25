package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.PlayerPackage.Player;

import java.util.*;

//not implemented yet
public class Round {
    private int num_round;
    private LinkedList<Player> players;
    private Match match;


    //constructor
    public Round ( int num_round, LinkedList<Player> players, Match match){
        this.match = match;
        this.num_round = num_round;
        this.players = players;
    }


    public synchronized void run() {
        //questo metodo prepara il round con i dadi della Riserva ecc...
        this.getMatch().getGametable().setupRound();

        // Primo giro
        for (Player player: this.players) {
            Turn turn = new Turn(player, this,1);
            final Thread thread = new Thread(turn);
            Timer timer  = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        //assuming it takes 20 secs to complete the task
                        Thread.sleep(1200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(thread.isAlive()){
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

        // Secondo giro
        Collections.reverse(this.players);
        for (Player player: this.players){
            Turn turn = new Turn(player, this,2);
            final Thread thread = new Thread(turn);
            Timer timer  = new Timer(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        //quanto vogliamo farlo durare un turno? 5 minuti?
                        Thread.sleep(1200000);
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

        // Ripristino l'ordine della lista di partenza
        Collections.reverse(this.players);

        //termino il round aggiornando il tracciato round prendendo i dadi dalla riserva
        try{
            this.getMatch().getGametable().endRound(this.num_round);
        }catch (RoundTrackException e){
            //nel caso in cui voglia aggiornare una casella del tracciato round che non esiste
            // per esempio la -1, o la 11
        }
    }



    //metodi getter e setter
    public Match getMatch() {
        return match;
    }
    public int getNum_round() {
        return num_round;
    }
    public LinkedList<Player> getPlayers() {
        return players;
    }
}
