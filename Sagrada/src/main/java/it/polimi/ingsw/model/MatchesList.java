package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.MatchStartedYetException;
import it.polimi.ingsw.model.ToolCard.Martelletto;

import java.io.Serializable;
import java.util.*;


//design pattern singleton
public class MatchesList implements Serializable {
    private static MatchesList instance;
    private Hashtable<String, Match> matches;

    //private constructor
    private MatchesList(){
        this.matches = new Hashtable<>();
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
        final Match match = new Match(player, game_name);
        synchronized (this){
            if(this.matches.containsKey(game_name)){
                throw new HomonymyException();
            }
            this.matches.put(game_name,match);
        }
        new Thread(match).start();
        return ;
    }

    /**
     * This method allows the user to join a match
     * @param player Player to be added to the match
     * @param game_name Game of the match you want to try to join
     * @throws GameNotExistantException Exception thrown if the specified name of the game is not found
     * @throws MatchStartedYetException Exception thrown if the match is already startec
     */
    public void  join(Player player, String game_name) throws GameNotExistantException, MatchStartedYetException {
        Match match = this.matches.get(game_name);
        if(match == null){
            throw new GameNotExistantException();
        }
        synchronized (match){
            if (match.isStarted()){
                throw new MatchStartedYetException();
            }
            player.setMatch(match);
            match.join(player);
            match.notifyAll();
        }

    }

    /**
     * @return List of active matches available in the server
     */
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

    protected void remove(Match match) {
        this.matches.remove(match.getName());
    }

    protected Match getMatch(String matchName) {
        return this.matches.get(matchName);
    }

    protected int getMatchesListSize() {
        return matches.size();
    }
}
