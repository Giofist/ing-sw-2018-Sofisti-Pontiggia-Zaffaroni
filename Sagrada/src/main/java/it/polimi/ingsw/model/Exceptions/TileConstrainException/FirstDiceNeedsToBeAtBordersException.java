package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class FirstDiceNeedsToBeAtBordersException extends TileConstrainException{
    private static final String msg = "3";
    public FirstDiceNeedsToBeAtBordersException() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
