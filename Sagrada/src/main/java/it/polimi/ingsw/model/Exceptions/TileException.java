package it.polimi.ingsw.model.Exceptions;

public class TileException extends Exception {
    private static final String msg = "you didn't respect some constrains about the SchemeCard";
    public TileException() {
        super(msg);
    }
    public TileException(String msg){ super(msg); }
}
