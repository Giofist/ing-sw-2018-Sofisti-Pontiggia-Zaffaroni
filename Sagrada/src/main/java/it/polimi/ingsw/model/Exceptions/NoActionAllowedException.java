package it.polimi.ingsw.model.Exceptions;

public class NoActionAllowedException extends Exception {
    private static final String msg = "33";
    public NoActionAllowedException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
