package it.polimi.ingsw.model.Exceptions;

public class NotEnoughSegnaliniException extends Exception{
    private static final String msg = "not enough segnalini";
    public NotEnoughSegnaliniException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
