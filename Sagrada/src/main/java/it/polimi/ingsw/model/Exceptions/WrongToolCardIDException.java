package it.polimi.ingsw.model.Exceptions;

public class WrongToolCardIDException extends Exception {
    private static final String msg = "Non c'è nessuna toolcard con questo ID nel deck di questa partita, mi spiace\n";
    public WrongToolCardIDException() {
        super(msg);
    }
    public WrongToolCardIDException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
