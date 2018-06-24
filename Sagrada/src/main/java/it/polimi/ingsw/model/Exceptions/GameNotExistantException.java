package it.polimi.ingsw.model.Exceptions;

public class GameNotExistantException extends Exception {
    private static final String msg = "27";
    public GameNotExistantException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
