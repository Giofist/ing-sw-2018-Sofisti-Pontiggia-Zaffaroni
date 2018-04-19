package it.polimi.ingsw.model;
import java.util.*;
import it.polimi.ingsw.Controller.*;


//design pattern singleton

//ho ignorato la questione dei timer, ne tratteremo
public class MultipleUserGameList {
    private static  MultipleUserGameList instance;
    private LinkedList<MultiplePlayerGameHandler> games;

    //costruttore privato
    private MultipleUserGameList(){
        this.games = new LinkedList<MultiplePlayerGameHandler>();
    }
    //metodo che crea/dà accesso se già creata all'unica istanza
    static MultipleUserGameList singleton(){
        if (instance == null)
        instance = new MultipleUserGameList();
        return instance;
    }


    //crea una partita
    public MultiplePlayerGameHandler create (User user, String game_name) throws HomonymyException{
        for (MultiplePlayerGameHandler previousGame: this.games){
            if (previousGame.getName()== game_name) {
                throw new HomonymyException();
                break;
            }
        }
        MultiplePlayerGameHandler game = new MultiplePlayerGameHandler(user, game_name);
        this.games.add(game);
        return game;
    }


    //cancella una partita dalla lista
    public void remove(MultiplePlayerGameHandler game){
        for (MultiplePlayerGameHandler otherGames : this.games ) {
            if (game.getName() == otherGames.getName())
                this.games.remove(otherGames);
        }
    }

}
