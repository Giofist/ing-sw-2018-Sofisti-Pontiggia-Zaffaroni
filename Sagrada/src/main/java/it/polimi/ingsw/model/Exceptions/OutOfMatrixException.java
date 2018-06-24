package it.polimi.ingsw.model.Exceptions;

public class OutOfMatrixException extends Exception {
    private static final String msg = "37";
    public OutOfMatrixException() {
        super(msg);
    }
    public OutOfMatrixException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
