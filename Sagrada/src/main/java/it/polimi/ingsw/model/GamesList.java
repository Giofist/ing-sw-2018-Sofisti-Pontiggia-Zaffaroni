package it.polimi.ingsw.model;
import java.util.*;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;


//design pattern singleton

//(pon 6/5/18) : ho modificato questa classe per garantire un acesso thread safe
//ho banalmente dichiarato tutti i metodi come synchronized
// non so se questo va bene: forse bisognerebbe inserire un lock o usare una collection.synchronized
//bisognerebbe testare e verificare
public class GamesList {
    private static GamesList instance;
    private List<Match> matches;

    //private constructor
    private GamesList(){
        this.matches = new LinkedList<Match>();
    }

    //singleton design pattern
    public  synchronized static GamesList singleton() {
        if (instance == null)
            instance = new GamesList();
        return instance;
    }



    //createGame a game and add to the existant list
    public synchronized Match createGame(Player player, String game_name) throws HomonymyException {
        for (Match previousMatch : this.matches){
            if (previousMatch.getName().equals(game_name) ) {
                throw new HomonymyException();
            }
        }
        Match match = new Match(player, game_name);
        this.matches.add(match);
        return match;
    }


    //delete a match (because it's finished)
    public  synchronized void remove(Match match){
        for (Match otherGames : this.matches) {
            if (match.getName() == otherGames.getName())
                this.matches.remove(otherGames);
        }
    }

    //get the list of the existant matches
    public List<Match> getgames(){
        return this.matches;
    }

    public Match getGame(String game_name) throws GameNotExistantException
    {
        for (Match match : this.matches){
            if(match.getName().equals(game_name)){
                return match;
            }
        }
        throw new GameNotExistantException();
    }



}
