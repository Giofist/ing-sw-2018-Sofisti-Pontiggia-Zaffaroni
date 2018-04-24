package it.polimi.ingsw.Controller;

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

    @Override
    public void run(){

    }


}
