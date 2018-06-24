package it.polimi.ingsw.model.Exceptions;

public class NotEnoughSegnaliniException extends Exception{
    private static final String msg = "35";
    public NotEnoughSegnaliniException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
