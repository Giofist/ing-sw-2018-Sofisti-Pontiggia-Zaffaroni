package it.polimi.ingsw.model.Exceptions;

public class NotEnoughSegnaliniException extends Exception{
    private static final String msg = "Non hai abbastanza segnalini per questa toolcard";
    public NotEnoughSegnaliniException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
