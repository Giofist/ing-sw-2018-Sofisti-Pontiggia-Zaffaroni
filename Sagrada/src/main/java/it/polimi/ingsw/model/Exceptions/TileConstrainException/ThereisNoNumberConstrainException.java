package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class ThereisNoNumberConstrainException extends TileConstrainException {
    private static final String msg = "Non c'è nessun requisito di numero sulla casella";
    public ThereisNoNumberConstrainException() {
        super(msg);
    }
    public ThereisNoNumberConstrainException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
