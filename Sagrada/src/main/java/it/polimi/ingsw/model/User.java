package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;


public class User {
    private String name;
    private String password;
    private Player player;
    private Observable userState;

    //constructor
    public User(String name, String password){
        this.name= name;
        this. password = password;
        this.player = null;
        this.userState = new UserState();
    }



    //metodi per la gestione dello stato
    public Observable getUserState() {
        return userState;
    }
    public boolean isActive() {
        if (this.userState.getState().equals(State.ACTIVE)){
            return true;
        }
        else return false;
    }
    public void setActive(boolean IsActive){
        if(IsActive){
            this.userState.setState(State.ACTIVE);
        }
        else{
            this.userState.setState(State.INACTIVE);
        }
    }

    //metodi getter e setter
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
    public void removePlayer(Observer observer){
        this.player.getPlayerState().removeObserver(observer);
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
