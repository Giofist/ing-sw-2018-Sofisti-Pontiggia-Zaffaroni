package it.polimi.ingsw.model.Exceptions;

public class DicepoolIndexException extends Exception {
    private static final String msg = "26";
    public DicepoolIndexException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
