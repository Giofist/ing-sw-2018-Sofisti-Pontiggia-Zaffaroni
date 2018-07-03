package it.polimi.ingsw.model.Exceptions;

public class NotEnoughTokenException extends Exception{
    private static final String msg = "35";
    public NotEnoughTokenException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
