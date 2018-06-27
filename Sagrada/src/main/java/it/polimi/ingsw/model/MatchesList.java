package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;

import java.util.*;


//design pattern singleton
public class MatchesList {
    private static MatchesList instance;
    private List<Match> matches;

    //private constructor
    private MatchesList(){
        this.matches = new LinkedList<>();
    }

    //singleton design pattern
    public static  MatchesList singleton() {
        if (instance == null)
            instance = new MatchesList();
        return instance;
    }



    //createMatch a game and add to the existant list
    public void createMatch(Player player, String game_name) throws HomonymyException {
        Timer timer  = new Timer(false);
        final Match match = new Match(player, game_name);
        synchronized (this){
            for (Match previousMatch : this.matches){
                if (previousMatch.getName().equals(game_name) ) {
                    throw new HomonymyException();
                }
            }

            this.matches.add(match);
        }
        //ogni match è un thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {

                    Thread.sleep(120000);
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
                    }
                }
            }
        },0);
        new Thread(match).start();
        return ;
    }

    public synchronized void  join(Player player, String game_name) throws GameNotExistantException {
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
