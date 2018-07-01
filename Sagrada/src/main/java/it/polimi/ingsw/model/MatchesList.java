package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.MatchStartedYetException;

import java.util.*;


//design pattern singleton
public class MatchesList {
    private static MatchesList instance;
    private Hashtable<String, Match> matches;

    //private constructor
    private MatchesList(){
        this.matches = new Hashtable<>();
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
            if(this.matches.containsKey(game_name)){
                throw new HomonymyException();
            }
            this.matches.put(game_name,match);
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
                synchronized (this){
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
                            matches.remove(match);
                            UsersList.Singleton().getUser(player.getName()).removePlayer(player.getPlayerState().getObserver());
                        }
                    }
                }

            }
        },0);
        new Thread(match).start();
        return ;
    }

    public synchronized void  join(Player player, String game_name) throws GameNotExistantException, MatchStartedYetException {
        Match match = this.matches.get(game_name);
        if(match == null){
            throw new GameNotExistantException();
        }
        if (match.isStarted()){
            throw new MatchStartedYetException();
        }
        player.setMatch(match);
        match.join(player);
    }


    public synchronized List<Match> getActiveMatches(){
        LinkedList<Match> activematches = new LinkedList<>();
        for(Match match: this.matches.values()){
            if (!match.isStarted()){
                activematches.add(match);
            }
        }
        return activematches;
    }

    //useful for testing?
    protected void clearMatchesList() {
        this.matches.clear();
    }

    public void remove(Match match) {
        this.matches.remove(match.getName());
    }
}
