package it.polimi.ingsw.model.Exceptions;

public class
PrivateGoalCardException extends Exception {
    private static final String msg = "Something went wrong with private goal Card: zorry mate!";
    public PrivateGoalCardException() {
        super(msg);
    }
    public PrivateGoalCardException(String msg) {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
