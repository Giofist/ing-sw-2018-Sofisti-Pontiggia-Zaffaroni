package it.polimi.ingsw.model.Exceptions;

public class EmpyDicepoolException extends Exception {
    private static final String msg = "Questo sacchetto dei dadi è vuoto!\n";
    public EmpyDicepoolException() {
        super(msg);
    }
    public EmpyDicepoolException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
