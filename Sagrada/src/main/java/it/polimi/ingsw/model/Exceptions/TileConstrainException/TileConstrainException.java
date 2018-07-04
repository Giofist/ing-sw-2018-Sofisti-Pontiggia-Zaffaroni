package it.polimi.ingsw.model.Exceptions.TileConstrainException;


public class TileConstrainException extends Exception {
    private static final String msg = "9";
    public TileConstrainException() {
        super(msg);
    }
    public TileConstrainException(String msg){ super(msg); }
    public static String getMsg() {
        return msg;
    }
}
