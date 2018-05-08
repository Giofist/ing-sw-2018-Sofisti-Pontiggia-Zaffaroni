package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class DiceSameIntensityNearYou  extends TileConstrainException {
    private static final String msg = "Non puoi mettere il dado qui perchè ce n'è uno della stessa intensità vicino\n";
    public DiceSameIntensityNearYou() {
        super(msg);
    }
    public DiceSameIntensityNearYou(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}