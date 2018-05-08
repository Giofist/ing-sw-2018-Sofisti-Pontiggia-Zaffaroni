package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class FirstDiceNeedsToBeAtBordersException extends TileConstrainException{
    private static final String msg = "Non puoi mettere un dado qui, perchè il primo dado deve stare sui bordi";
    public FirstDiceNeedsToBeAtBordersException() {
        super(msg);
    }
    public FirstDiceNeedsToBeAtBordersException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
