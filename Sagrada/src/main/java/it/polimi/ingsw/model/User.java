package it.polimi.ingsw.model;
import java.net.*;
import java.util.*;

public class User {
    private String name;
    private Socket socket;
    private String password;
    private String news_to_show;

    //constructor
    public User(String name, String password){
        this.name= name;
        this. password = password;
    }

    //questo nel caso il giocatore si sia disconnesso prima del termine della partita,
    // appena si connette viene mostrato un messaggio diverso.
    public String news(){
        if (this.news_to_show == null)
            return "Ready to start";
        else return this.news_to_show;
    }
    public void setNews_to_show(String message){
        this.news_to_show = message;
    }



    //metodi getter e setter
    public String getName(){
        return this. name;
    }
    public void setName(Socket socket){
        this.socket= socket;
    }
    public Socket getSocket() {
        return this.socket;
    }
    public void setSocket(Socket socket){
        this.socket=socket;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password= password;
    }


}
