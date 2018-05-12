package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.RoundTrackException;

import java.util.LinkedList;
//not implemented yet
public class Round {
    private int num_round;
    private LinkedList<Player> players;
    private Game game;



    //constructor
    public Round ( int num_round, LinkedList<Player> players, Game game){
        this.game = game;
        this.num_round = num_round;
        this.players = players;
    }


    public void run(){
        //questo metodo prepara il round con i dadi della Riserva ecc...
        this.getGame().getGametable().setupRound();

        //qui la chiamata ai vari turn



        //termino il round aggiornando il tracciato round prendendo i dadi dalla riserva
        try{
            this.getGame().getGametable().endRound(this.num_round);
        }catch (RoundTrackException e){
            //nel caso in cui voglia aggiornare una casella del tracciato round che non esiste
            // per esempio la -1, o la 11
        }

    }



    //metodi getter e setter
    public Game getGame() {
        return game;
    }
    public int getNum_round() {
        return num_round;
    }
    public LinkedList<Player> getPlayers() {
        return players;
    }
}
