package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.PlayerPackage.Player;
import it.polimi.ingsw.model.PlayerPackage.State;

import java.util.*;


//design pattern singleton

//(pon 6/5/18) : ho modificato questa classe per garantire un acesso thread safe
//ho banalmente dichiarato tutti i metodi come synchronized
// non so se questo va bene: forse bisognerebbe inserire un lock o usare una collection.synchronized
//bisognerebbe testare e verificare
public class MatchesList {
    private static MatchesList instance;
    private List<Match> matches;

    //private constructor
    private MatchesList(){
        this.matches = new LinkedList<>();
    }

    //singleton design pattern
    public static synchronized MatchesList singleton() {
        if (instance == null)
            instance = new MatchesList();
        return instance;
    }



    //createMatch a game and add to the existant list
    public synchronized void createMatch(Player player, String game_name) throws HomonymyException {
        for (Match previousMatch : this.matches){
            if (previousMatch.getName().equals(game_name) ) {
                throw new HomonymyException();
            }
        }
        Timer timer  = new Timer(false);
        final Match match = new Match(player, game_name);
        this.matches.add(match);
        //ogni match è un thread
        final Thread thread = new Thread(match);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {

                    Thread.sleep(900000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!match.isStarted()){
                    if (match.getallPlayers().size() >1){
                        match.isreadyTostart = true;
                        match.notifyAll();
                    }
                    else{
                        //l'unico giocatore deve abbandonare la partita
                        for(Player player: match.getallPlayers()){
                            player.setPlayerState(State.ERRORSTATE);
                        }
                        thread.interrupt();
                    }
                }
            }
        },0);
        new Thread(match).start();
        return ;
    }

    public synchronized void  join(Player player, String game_name) throws GameNotExistantException {
        System.out.println(game_name);
        Match match = this.getMatch(game_name);
        player.setMatch(match);
        match.join(player);
    }



    //get the list of the existant matches
    public List<Match> getmatches(){
        return this.matches;
    }
    public List<Match> getActiveMatches(){
        LinkedList<Match> activematches = new LinkedList<>();
        for(Match match: this.matches){
            if (!match.isStarted()){
                activematches.add(match);
            }
        }
        return activematches;
    }


    public Match getMatch(String game_name) throws GameNotExistantException {
        for (Match match : this.matches){
            if(match.getName().equals(game_name)){
                return match;
            }
        }
        throw new GameNotExistantException();
    }

    protected void clearMatchesList() {
        this.matches.removeAll(this.matches);
    }

}
