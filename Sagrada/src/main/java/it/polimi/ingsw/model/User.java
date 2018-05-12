package it.polimi.ingsw.model;

import java.net.*;

public class User {
    private String name;
    private Socket socket;
    private String password;
    private Player player;

    //constructor
    public User(String name, String password){
        this.name= name;
        this. password = password;
        this.player = null;

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

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
