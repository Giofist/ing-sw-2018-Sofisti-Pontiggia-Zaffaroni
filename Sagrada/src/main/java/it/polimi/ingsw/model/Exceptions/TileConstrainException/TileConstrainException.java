package it.polimi.ingsw.model.Exceptions.TileConstrainException;

//questa classe viene estesa da errori specifici, consiglio di usarla per gli errori posibili relativi alla scheme card
//e al piazzamneto dei dadi

public class TileConstrainException extends Exception {
    private static final String msg = "9";
    public TileConstrainException() {
        super(msg);
    }
    public TileConstrainException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
