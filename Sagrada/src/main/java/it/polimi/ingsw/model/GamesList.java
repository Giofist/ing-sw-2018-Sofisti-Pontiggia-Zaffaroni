package it.polimi.ingsw.model;
import java.rmi.RemoteException;
import java.util.*;

import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import java.lang.Thread;


//design pattern singleton

//(pon 6/5/18) : ho modificato questa classe per garantire un acesso thread safe
//ho banalmente dichiarato tutti i metodi come synchronized
// non so se questo va bene: forse bisognerebbe inserire un lock o usare una collection.synchronized
//bisognerebbe testare e verificare
public class GamesList {
    private static GamesList instance;
    private List<Game> games;

    //private constructor
    private GamesList(){
        this.games = new LinkedList<Game>();
    }

    //singleton design pattern
    public  synchronized static GamesList singleton() {
        if (instance == null)
            instance = new GamesList();
        return instance;
    }



    //createGame a game and add to the existant list
    public synchronized Game createGame(Player player, String game_name) throws HomonymyException {
        for (Game previousGame: this.games){
            if (previousGame.getName().equals(game_name) ) {
                throw new HomonymyException();
            }
        }
        Game game = new Game(player, game_name);
        this.games.add(game);
        return game;
    }


    //delete a game (because it's finished)
    public  synchronized void remove(Game game){
        for (Game otherGames : this.games ) {
            if (game.getName() == otherGames.getName())
                this.games.remove(otherGames);
        }
    }

    //get the list of the existant games
    public List<Game> getgames(){
        return this.games;
    }

    public Game getGame(String game_name) throws GameNotExistantException
    {
        for (Game game: this.games){
            if(game.getName().equals(game_name)){
                return game;
            }
        }
        throw new GameNotExistantException();
    }



}
