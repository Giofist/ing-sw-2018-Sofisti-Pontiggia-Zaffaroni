package it.polimi.ingsw.model.Exceptions;

public class NumberOfPlayersNotAllowedException extends Exception {
    private static final String msg = "36";
    public NumberOfPlayersNotAllowedException () {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}
