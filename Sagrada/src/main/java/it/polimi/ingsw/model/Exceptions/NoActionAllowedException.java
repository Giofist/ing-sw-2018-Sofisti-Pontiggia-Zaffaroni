package it.polimi.ingsw.model.Exceptions;

public class NoActionAllowedException extends Exception {
    private static final String msg = "Non puoi eseguire nessuna azione, mi spiace\n";
    public NoActionAllowedException() {
        super(msg);
    }
    public NoActionAllowedException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
