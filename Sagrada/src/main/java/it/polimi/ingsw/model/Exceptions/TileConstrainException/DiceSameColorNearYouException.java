package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class DiceSameColorNearYouException extends TileConstrainException {
    private static final String msg = "1";
    public DiceSameColorNearYouException() {
        super(msg);
    }
}
