package it.polimi.ingsw.model.Exceptions;

public class UserNotExistantException extends Exception {
    private static final String msg = "Nessun utente registrato con questo nome\n";
    public UserNotExistantException() {
        super(msg);
    }
    public UserNotExistantException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
