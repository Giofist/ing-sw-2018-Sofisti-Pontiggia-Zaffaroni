package it.polimi.ingsw.model;

import it.polimi.ingsw.model.PlayerPackage.Player;

public class User {
    private String name;
    private String password;
    private Player player;
    private boolean active;

    //constructor
    public User(String name, String password){
        this.name= name;
        this. password = password;
        this.player = null;
        this.active = false;

    }

    //metodi getter e setter

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean IsActive){
        this.active = IsActive;
    }
    public String getName(){
        return this. name;
    }
    public void setName(String name){ this.name = name; }
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
    public void removePlayer(){
        this.player = null;
    }

    @Override
    public boolean equals(Object o){
        if (o.toString().equals(this.toString())){
            return true;
        }
        else return false;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
