package it.polimi.ingsw.Controller;

import java.util.*;
import it.polimi.ingsw.model.User;


//non terminata
public class MultiplePlayerGameHandler {
    String game_name;
    LinkedList<User> users;

    public MultiplePlayerGameHandler(User user, String game_name){
        this.game_name = game_name;
        this.users.addFirst(user);
    }
    public String getName(){
        return this.game_name;
    };
}
