package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class NotRespectedNumberConstrainException extends TileConstrainException {
    private static final String msg = "5";
    public NotRespectedNumberConstrainException() { super(msg); }

    public static String getMsg() {
        return msg;
    }
}
