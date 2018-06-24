package it.polimi.ingsw.model.Exceptions;

public class NotAllowedActionException extends Exception {
    private static final String msg = "34";
    public NotAllowedActionException() {
        super(msg);
    }
    public NotAllowedActionException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
