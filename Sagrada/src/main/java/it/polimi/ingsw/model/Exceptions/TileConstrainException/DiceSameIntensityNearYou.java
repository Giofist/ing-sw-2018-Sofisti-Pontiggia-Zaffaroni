package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class DiceSameIntensityNearYou  extends TileConstrainException {
    private static final String msg = "2";
    public DiceSameIntensityNearYou() {
        super(msg);
    }

    public static String getMsg() {
        return msg;
    }
}