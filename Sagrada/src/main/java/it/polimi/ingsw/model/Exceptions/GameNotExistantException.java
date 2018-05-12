package it.polimi.ingsw.model.Exceptions;

public class GameNotExistantException extends Exception {
    private static final String msg = "Non esiste nessuna partita con questo nome!\n";
    public GameNotExistantException() {
        super(msg);
    }
    public GameNotExistantException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
