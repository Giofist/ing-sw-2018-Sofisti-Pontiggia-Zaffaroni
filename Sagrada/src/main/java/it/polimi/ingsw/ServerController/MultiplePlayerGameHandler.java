package it.polimi.ingsw.ServerController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import it.polimi.ingsw.model.User;


//non terminata
//notate bene che da specifica il timer deve essere caricato da file
//l'idea è che ogni partita sia gestita da un thread, per questo impleemnta runnable e ha un metodo run
public class MultiplePlayerGameHandler extends UnicastRemoteObject implements Runnable,RMIRemoteServerClientHandler {
    private String game_name;
    private LinkedList<User> users;
    private int maxNumberPlayers;

    //public constructor
    public MultiplePlayerGameHandler (User user, String game_name, int  max) throws RemoteException {
        this.game_name = game_name;
        this.users.addFirst(user);
        this.maxNumberPlayers = max;
    }


    public String getName(){
        return this.game_name;
    }
    public int getMaxNumberPlayers(){
        return maxNumberPlayers;
    }
    public int getActualNumberOfPlayers(){
        return this.users.size();
    }
    public void join(User user){
        this.users.add(user);
    }
    @Override
    public void run(){

    }



}
