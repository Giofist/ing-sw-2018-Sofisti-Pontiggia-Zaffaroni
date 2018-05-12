package it.polimi.ingsw.ServerController;

import it.polimi.ingsw.ClientController.FeedObserverView;
import it.polimi.ingsw.ClientController.ObserverView;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Exceptions.GameNotExistantException;
import it.polimi.ingsw.model.Exceptions.HomonymyException;
import it.polimi.ingsw.model.Exceptions.UserNotExistantException;
import it.polimi.ingsw.model.SchemeDeck.SchemeCard;

import java.awt.image.ImageObserver;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

//all'inizio avevo pensato di mettere il client server per rmi in una classe a parte
//poi ho pensato: ma devo proprio? beh per ora mi sembra di no
//l'unica cosa è l'esposizione del metodo run, che beh nel caso dirmi non deve essere invocato, perchè serve invece per gestire le connessioni socket
// il fatto che implementi runnable e quindi sia codice per thread non mi pare crei problemi
//se avete notizie avvisatemi
//client handler
public class ClientHandler extends UnicastRemoteObject implements Runnable, RmiServerInterface {

    private Socket socket;
    public ClientHandler ()throws RemoteException {};
    public ClientHandler(Socket socket) throws RemoteException{
        this.socket = socket;
    }

    @Override
    public void run(){

        //questo per gestire i socket
        //qui metteremo lo switchone
    }


    @Override
    public String rmiTest(String stringa) {
        return "Test " + stringa;
    }

    // Here we'll put the implementation of all the methods in the interface RmiServerInterface

    // Implementing the register method
    @Override
    synchronized public  void register(String username, String password) throws RemoteException{
        // When the User wants to register a new account we first verify that there isn't another User with the same username
        try {
            UsersList.Singleton().checkHomonymy(username);
        } catch (HomonymyException e) {
            throw new RemoteException("Username already in use\n");
        }

        // Then we proceed to register and notify the new User
        UsersList.Singleton().register(username, password);
    }


    // Implementing the login method
    @Override
    synchronized public void login(String username, String password) throws RemoteException{
         if(UsersList.Singleton().check(username, password) == false){
             throw new RemoteException("Invalid username or password");
         }
    }


    @Override
    public void createGame(String clientname, ObserverView client, FeedObserverView Client, String gamename ) throws  RemoteException {
        try {
            //creo un player, lo associo ad uno user e viceversa;
            Player player = new Player();
            User user = UsersList.Singleton().getUser(clientname);
            user.setPlayer(new Player());
            player.setUser(user);

            //creo effettivamente la partita
            //NB: questa chiamata già aggiunge in player un riferimento alla partita a cui è iscritto
            GamesList.singleton().createGame(player, gamename);

            //observer pattern
            player.feedObserverViews(Client);
            player.observerViews(client);
            //gestione delle eccezioni
        } catch (HomonymyException e) {
            throw new RemoteException(e.getMsg());
        } catch (UserNotExistantException err) {
            throw new RemoteException(err.getMessage());
        }
    }
    public void joinaGame(String clientname, String gamename) throws RemoteException{
            try{
                Player player = UsersList.Singleton().getUser(clientname).getPlayer();
                Game game = GamesList.singleton().getGame(gamename);
                //NB questa chiamata già aggiunge un riferimento a questo match in player
                game.join(player);
            }catch (UserNotExistantException e){
                throw new RemoteException(e.getMessage());
            }catch (GameNotExistantException e){
                throw new RemoteException(e.getMessage());
            }
    }

    @Override
    public void setSchemeCard(String clientusername, int twin, SchemeCard schemecard) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientusername).getPlayer();
            switch (twin) {
                case 0: player.setScheme(schemecard);break;
                case 1: player.setScheme(schemecard.getTwinCard()); break;
            }
        }catch (UserNotExistantException e){
                throw new RemoteException(e.getMessage());
        }
    }


    public GoalCard getPrivateGoalCard(String clientname) throws RemoteException{
        try{
            Player player = UsersList.Singleton().getUser(clientname).getPlayer();
            return player.getPrivateGoalCard();
        }catch (UserNotExistantException e){
            throw new RemoteException(e.getMessage());
        }

    }

    // Implementing the getActiveMatchList
}