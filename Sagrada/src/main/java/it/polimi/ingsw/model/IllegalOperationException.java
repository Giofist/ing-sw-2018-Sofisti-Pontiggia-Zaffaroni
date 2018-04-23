package it.polimi.ingsw.model;

public class IllegalOperationException extends Exception {
    private static final String msg = "you can't do that";
    public IllegalOperationException() {
        super(msg);
    }
}
