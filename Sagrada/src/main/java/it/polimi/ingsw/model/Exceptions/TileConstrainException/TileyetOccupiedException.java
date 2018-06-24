package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class TileyetOccupiedException extends TileConstrainException {
    private static final String msg = "10";
    public TileyetOccupiedException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
