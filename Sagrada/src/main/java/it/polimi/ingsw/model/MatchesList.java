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


    /**
     * @return This method returns a Singleton instance of the matches list present in the server
     */
    public static  MatchesList singleton() {
        if (instance == null)
            instance = new MatchesList();
        return instance;
    }


    /**
     * This method allows to create a new match and adds it to the list
     * @param player Player creator
     * @param game_name Name of the match to be created
     * @throws HomonymyException Exception thrown when the specified name for the match il already in use
     */
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
        // Each Match is a separate thread
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
                        // The only player must leave the game
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


    /**
     * This method allows the user to join a match
     * @param player Player to be added to the match
     * @param game_name Game of the match you want to try to join
     * @throws GameNotExistantException Exception thrown if the specified name of the game is not found
     */
    public synchronized void  join(Player player, String game_name) throws GameNotExistantException {
        Match match = this.getMatch(game_name);
        player.setMatch(match);
        match.join(player);
    }


    /**
     * @return List of matches available in the server
     */
    public List<Match> getmatches(){
        return this.matches;
    }


    /**
     * @return List of active matches available in the server
     */
    public List<Match> getActiveMatches(){
        LinkedList<Match> activematches = new LinkedList<>();
        for(Match match: this.matches){
            if (!match.isStarted()){
                activematches.add(match);
            }
        }
        return activematches;
    }


    /**
     * @param game_name Name of the match to return
     * @return The specified match if available
     * @throws GameNotExistantException Exception thrown when the specified name doesn't correspond to a match in the list
     */
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
