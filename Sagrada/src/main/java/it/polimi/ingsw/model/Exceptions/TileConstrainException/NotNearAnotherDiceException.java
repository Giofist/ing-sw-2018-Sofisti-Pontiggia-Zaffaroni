package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class NotNearAnotherDiceException extends TileConstrainException {
    private static final String msg = "4";
    public NotNearAnotherDiceException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
