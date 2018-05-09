package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class TileyetOccupiedException extends TileConstrainException {
    private static final String msg = "Non puoi mettere un dado qui, perchè c'è già un dado su questa casella";
    public TileyetOccupiedException() {
        super(msg);
    }
    public TileyetOccupiedException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
