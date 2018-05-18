package it.polimi.ingsw.model.Exceptions;

public class TwoDiceSameColorException extends Exception {
    private static final String msg = "Ci sono due dadi con lo stesso colore\n";
    public TwoDiceSameColorException() {
        super(msg);
    }
    public TwoDiceSameColorException(String msg){ super(msg); }


}
