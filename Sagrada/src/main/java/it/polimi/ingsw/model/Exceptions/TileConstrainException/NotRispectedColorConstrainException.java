package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class NotRispectedColorConstrainException extends TileConstrainException {
    private static final String msg = "6";
    public NotRispectedColorConstrainException() { super(msg); }

    public static String getMsg() {
        return msg;
    }
}
