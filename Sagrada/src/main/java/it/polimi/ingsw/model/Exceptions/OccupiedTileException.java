package it.polimi.ingsw.model.Exceptions;

public class OccupiedTileException extends TileException {
    private static final String msg = "this tile is already occupied by a dice";
    public OccupiedTileException() { super(msg); }
}
