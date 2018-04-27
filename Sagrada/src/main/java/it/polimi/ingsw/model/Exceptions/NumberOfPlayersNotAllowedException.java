package it.polimi.ingsw.model.Exceptions;

public class NumberOfPlayersNotAllowedException extends Exception {
    private static final String msg = "Number of players not allowed\n";
    public NumberOfPlayersNotAllowedException () {
        super(msg);
    }
}
