package it.polimi.ingsw.model.Exceptions;

public class
PrivateGoalCardException extends Exception {
    private static final String msg = "39";
    public PrivateGoalCardException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
