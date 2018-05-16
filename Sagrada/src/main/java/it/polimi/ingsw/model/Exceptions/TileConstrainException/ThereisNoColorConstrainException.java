package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class ThereisNoColorConstrainException extends TileConstrainException {
    private static final String msg = "Non c'è nessun requisito di colore sulla casella\n";
    public ThereisNoColorConstrainException() {
        super(msg);
    }
    public ThereisNoColorConstrainException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
