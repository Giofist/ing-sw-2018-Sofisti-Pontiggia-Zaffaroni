package it.polimi.ingsw.model;
import java.util.*;
import it.polimi.ingsw.Controller.*;


//design pattern singleton

//ho ignorato la questione dei timer, ne tratteremo
public class MultipleUserGameList {
    private static  MultipleUserGameList instance;
    private LinkedList<MultiplePlayerGameHandler> games;

    //private constructor
    private MultipleUserGameList(){
        this.games = new LinkedList<MultiplePlayerGameHandler>();
    }
    //method to access/create the unique instance of the class
    static MultipleUserGameList singleton(){
        if (instance == null)
        instance = new MultipleUserGameList();
        return instance;
    }


    //create a game and add to the existant list
    public MultiplePlayerGameHandler create (User user, String game_name, int max) throws HomonymyException{
        for (MultiplePlayerGameHandler previousGame: this.games){
            if (previousGame.getName()== game_name) {
                throw new HomonymyException();
            }
        }
        MultiplePlayerGameHandler game = new MultiplePlayerGameHandler(user, game_name,max);
        this.games.add(game);
        return game;
    }


    //delete a game (because it's finished)
    public void remove(MultiplePlayerGameHandler game){
        for (MultiplePlayerGameHandler otherGames : this.games ) {
            if (game.getName() == otherGames.getName())
                this.games.remove(otherGames);
        }
    }

    //get the list of the existant games
    public LinkedList<MultiplePlayerGameHandler> getgames(){
        return this.games;
    }


}
