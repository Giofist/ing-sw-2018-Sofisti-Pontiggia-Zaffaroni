package it.polimi.ingsw.model.Exceptions;

public class OutOfMatrixException extends Exception {
    private static final String msg = "Sei fuori dalla vetrata!\n";
    public OutOfMatrixException() {
        super(msg);
    }
    public OutOfMatrixException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
