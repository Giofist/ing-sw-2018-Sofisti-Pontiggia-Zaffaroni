package it.polimi.ingsw.model.Exceptions;

public class DiceNotExistantException extends Exception {
    private static final String msg = "this tile hasn't a dice";
    public DiceNotExistantException() {
        super(msg);
    }
    public DiceNotExistantException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
