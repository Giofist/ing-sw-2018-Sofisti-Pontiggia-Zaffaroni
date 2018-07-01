package it.polimi.ingsw.model.Exceptions;

public class MatchStartedYetException extends Exception {
    private static final String msg = "36";
    public MatchStartedYetException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
