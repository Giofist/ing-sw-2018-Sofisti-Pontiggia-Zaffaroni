package it.polimi.ingsw.model.Exceptions;

public class TwoDiceSameColorException extends Exception {
    private static final String msg = "two dices same color\n";
    public TwoDiceSameColorException() {
        super(msg);
    }
    public TwoDiceSameColorException(String msg){ super(msg); }
}
