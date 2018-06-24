package it.polimi.ingsw.model.Exceptions;

public class TwoDiceSameColorException extends Exception {
    private static final String msg = "42";
    public TwoDiceSameColorException() {
        super(msg);
    }
    public TwoDiceSameColorException(String msg){ super(msg); }


}
