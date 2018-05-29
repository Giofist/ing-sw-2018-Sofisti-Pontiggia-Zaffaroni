package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.RoundTrackException;
import it.polimi.ingsw.model.Turn.Turn;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.LinkedList;

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


    public void run() throws RemoteException, InterruptedException {
        //questo metodo prepara il round con i dadi della Riserva ecc...
        this.getMatch().getGametable().setupRound();

        //qui la chiamata ai vari turn
        //questa notifica verrà tolta
        for (Player player: this.players){
            try{
                player.notifyError("Questo è il round"+ this.num_round);
            }catch(RemoteException e){
                //do nothing
            }

        }

        // Primo giro
        for (Player player: this.players) {
            new Turn(player, this).run();
        }

        // Secondo giro
        Collections.reverse(this.players);
        for (Player player: this.players){
            new Turn(player, this).run();
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
