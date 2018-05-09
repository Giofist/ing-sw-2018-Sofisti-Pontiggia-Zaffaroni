package it.polimi.ingsw.model.Exceptions;

public class TwoDiceSameShadeException extends Exception {
    private static final String msg = "two dices same shade\n";
    public TwoDiceSameShadeException() {
        super(msg);
    }
    public TwoDiceSameShadeException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
