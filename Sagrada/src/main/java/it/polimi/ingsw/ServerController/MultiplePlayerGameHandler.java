package it.polimi.ingsw.ServerController;

import java.util.*;
import it.polimi.ingsw.model.User;


//non terminata
public class MultiplePlayerGameHandler extends  TimerTask implements Runnable {
    private String game_name;
    private LinkedList<User> users;
    private int maxNumberPlayers;

    //public constructor
    public MultiplePlayerGameHandler(User user, String game_name, int  max){
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
