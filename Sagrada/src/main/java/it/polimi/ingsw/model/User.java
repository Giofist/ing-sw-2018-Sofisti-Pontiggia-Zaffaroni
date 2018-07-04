package it.polimi.ingsw.model;

import it.polimi.ingsw.ClientView.Observer;


public class User {
    private String name;
    private String password;
    private Player player;
    private Observable userState;


    /**
     * This constructor creates a new user and sets its state to inactive
     * @param name The name we want to assign to the user, this will be the username used for logging in
     * @param password The password required when in future we want to use this user
     */
    public User(String name, String password){
        this.name= name;
        this. password = password;
        this.player = null;
        this.userState = new UserState();
    }



    // Methods used to manage the user's state


    /**
     * @return The state in which the player is currently set
     */
    public Observable getUserState() {
        return userState;
    }


    /**
     * This method returns true if the user is active at the moment
     * @return The active state
     */
    public boolean isActive() {
        if (this.userState.getState().equals(State.ACTIVE)){
            return true;
        }
        else return false;
    }


    /**
     * @param IsActive Set whether the user is active or not
     */
    public void setActive(boolean IsActive){
        if(IsActive){
            this.userState.setState(State.ACTIVE);
        }
        else{
            this.userState.setState(State.INACTIVE);
        }
    }


    // Getters and setters methods


    /**
     * @return The name of the user
     */
    public String getName(){
        return this. name;
    }


    /**
     * @param name The name we want to set to the user
     */
    public void setName(String name){ this.name = name; }


    /**
     * @return The password currently set by the user
     */
    public String getPassword(){
        return this.password;
    }


    /**
     * @param password The password to be assigned to the user
     */
    public void setPassword(String password){
        this.password= password;
    }


    /**
     * This method sets the corresponding player to the user when the latter enters a new match
     * @param player The player we want to associate to the user
     */
    public void setPlayer(Player player){
        this.player = player;
    }


    /**
     * @return The player associated to the user
     */
    public Player getPlayer() {
        return player;
    }


    /**
     * Remove another player from observing the user
     * @param observer The observer that no longer wants to receive updates from the user
     */
    public void removePlayer(Observer observer){
        this.player.getPlayerState().removeObserver(observer);
        this.player = null;
    }


    /**
     * Comparison to verify if two users are the same one
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if(o != null && o.toString().equals(this.toString())){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.name;
    }


}
