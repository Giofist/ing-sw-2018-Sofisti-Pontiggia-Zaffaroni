package it.polimi.ingsw.model.Exceptions;

public class WrongToolCardIDException extends Exception {
    private static final String msg = "45";
    public WrongToolCardIDException() {
        super(msg);
    }
    public WrongToolCardIDException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
