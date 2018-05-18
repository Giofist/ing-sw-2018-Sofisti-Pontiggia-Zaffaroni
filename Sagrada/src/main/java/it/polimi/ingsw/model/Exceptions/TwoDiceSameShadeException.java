package it.polimi.ingsw.model.Exceptions;

public class TwoDiceSameShadeException extends Exception {
    private static final String msg = "Ci sono due dadi con la stessa intensità!\n";
    public TwoDiceSameShadeException() {
        super(msg);
    }
    public TwoDiceSameShadeException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
