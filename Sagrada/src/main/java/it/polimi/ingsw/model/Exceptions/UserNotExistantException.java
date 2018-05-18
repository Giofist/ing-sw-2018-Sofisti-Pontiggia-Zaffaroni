package it.polimi.ingsw.model.Exceptions;

public class UserNotExistantException extends Exception {
    private static final String msg = "Nessun utente registrato con questo nome: il software lato client ha salvato male il clientname o questo sul server è stato rimosso, " +
            "la chiamata remota comunque non ha avuto effetto perchè non è stato trovato il player associato al client che ha eseeguito la chiamata stessa\n";
    public UserNotExistantException() {
        super(msg);
    }
    public UserNotExistantException(String msg){ super(msg); }

    public static String getMsg() {
        return msg;
    }
}
