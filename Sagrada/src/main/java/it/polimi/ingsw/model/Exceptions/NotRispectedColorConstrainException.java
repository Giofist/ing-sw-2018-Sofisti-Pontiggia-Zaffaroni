package it.polimi.ingsw.model.Exceptions;

public class NotRispectedColorConstrainException extends TileConstrainException {
    private static final String msg = "you aren't respecting the tile color constrain";
    public NotRispectedColorConstrainException() { super(msg); }
}
