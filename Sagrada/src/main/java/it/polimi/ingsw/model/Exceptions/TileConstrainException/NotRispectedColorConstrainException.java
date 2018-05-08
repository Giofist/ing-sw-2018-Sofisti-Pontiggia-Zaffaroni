package it.polimi.ingsw.model.Exceptions.TileConstrainException;

import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;

public class NotRispectedColorConstrainException extends TileConstrainException {
    private static final String msg = "you aren't respecting the tile color constrain";
    public NotRispectedColorConstrainException() { super(msg); }

    public static String getMsg() {
        return msg;
    }
}
