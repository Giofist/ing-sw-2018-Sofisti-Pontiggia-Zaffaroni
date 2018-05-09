package it.polimi.ingsw.model.Exceptions.TileConstrainException;

public class DiceSameColorNearYouException extends TileConstrainException {
    private static final String msg = "Non puoi mettere il dado qui perchè ce n'è uno dello stesso colore vicino\n";
    public DiceSameColorNearYouException() {
        super(msg);
    }
    public DiceSameColorNearYouException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
