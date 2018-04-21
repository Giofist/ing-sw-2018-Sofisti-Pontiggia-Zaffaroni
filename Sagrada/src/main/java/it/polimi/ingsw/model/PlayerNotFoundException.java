package it.polimi.ingsw.model;

public class PlayerNotFoundException extends Exception {
    private static final String msg = "This player is not in your game";
    public PlayerNotFoundException() {
        super(msg);
    }
}
