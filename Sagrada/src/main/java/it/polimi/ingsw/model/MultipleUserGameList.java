package it.polimi.ingsw.model;
import java.rmi.RemoteException;
import java.util.*;
import it.polimi.ingsw.ServerController.*;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import java.lang.Thread;


//design pattern singleton

//(pon 6/5/18) : ho modificato questa classe per garantire un acesso thread safe
//ho banalmente dichiarato tutti i metodi come synchronized
// non so se questo va bene: forse bisognerebbe inserire un lock o usare una collection.synchronized
//bisognerebbe testare e verificare
public class MultipleUserGameList {
    private static  MultipleUserGameList instance;
    private List<MultiplePlayerGameHandler> games;

    //private constructor
    //questo è un modo per ritornare
    private MultipleUserGameList(){
        this.games = new LinkedList<MultiplePlayerGameHandler>();
    }
    //method to access/create the unique instance of the class
    //qui per sincronizzare utilizzo il lock intrinseco che possiede ogni classe, e nello speficico la classe MultipleUserGameList
    public  synchronized static MultipleUserGameList singleton() {
        if (instance == null)
            instance = new MultipleUserGameList();
        return instance;
    }



    //create a game and add to the existant list
    //secondo voi è giusto che qui la RemoteException venga rilanciata? Ne avremo un'idea più chiara quando vedrmeo bene come fare la classe GameHandler
    public synchronized MultiplePlayerGameHandler create (User user, String game_name, int max) throws HomonymyException,RemoteException {
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
    public  synchronized void remove(MultiplePlayerGameHandler game){
        for (MultiplePlayerGameHandler otherGames : this.games ) {
            if (game.getName() == otherGames.getName())
                this.games.remove(otherGames);
        }
    }

    //get the list of the existant games
    public synchronized List<MultiplePlayerGameHandler> getgames(){
        return this.games;
    }

    //check if a game is ready to start
    public synchronized void checkIsReady(){
        for (MultiplePlayerGameHandler game: this.games) {
            if(game.getActualNumberOfPlayers() == game.getMaxNumberPlayers()){
                new Thread(game).start();
                //qui ho pensato di instanziare un thread per ogni partita, qui dovremo mettere il codice per verificare se è un RMI, e quindi bisogna esportare la classe sul registro
                //oppure un socket, e quindi bisogna comunicare lungo il socket
                //la domanda è: ma se abbiamo dei giocatori connessi in socket e dei giocatori connessi in RMI?
                //bel casino eh? secondo me il fatto di avere dei turnhandler che si occupino di ciascun player ci aiuta molto, potremmo pensare di mettere degli adapter che si occupino della
                //connessione ma non mi sembra uan soluzione ottimale
            }
        }
    }


}
