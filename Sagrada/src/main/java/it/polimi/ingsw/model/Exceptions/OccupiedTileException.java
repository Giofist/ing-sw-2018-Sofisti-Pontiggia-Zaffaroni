package it.polimi.ingsw.model.Exceptions;

import it.polimi.ingsw.model.Exceptions.TileConstrainException.TileConstrainException;

public class OccupiedTileException extends TileConstrainException {
    private static final String msg = "this tile is already occupied by a dice";
    public OccupiedTileException() { super(msg); }
}
