package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class NotRespectedNumberConstrainException extends TileConstrainException {
    private static final String msg = "you aren't respecting the tile number constrain";
    public NotRespectedNumberConstrainException() { super(msg); }

    public static String getMsg() {
        return msg;
    }
}
