package it.polimi.ingsw.model.Exceptions;

public class UserNotExistentException extends Exception {
    private static final String msg = "44";
    public UserNotExistentException() {
        super(msg);
    }
    public UserNotExistentException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
