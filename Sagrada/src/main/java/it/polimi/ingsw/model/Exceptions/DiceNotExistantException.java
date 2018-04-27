package it.polimi.ingsw.model.Exceptions;

public class DiceNotExistantException extends Exception {
    private static final String msg = "this tile hasn't a tile";
    public DiceNotExistantException() {
        super(msg);
    }
    public DiceNotExistantException(String msg){ super(msg); }
}
