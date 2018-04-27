package it.polimi.ingsw.model.Exceptions;

public class TileConstrainException extends Exception {
    private static final String msg = "you didn't respect some constrains about the SchemeCard";
    public TileConstrainException() {
        super(msg);
    }
    public TileConstrainException(String msg){ super(msg); }
}
